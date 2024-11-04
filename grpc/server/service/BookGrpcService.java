@GrpcService(interceptors = { AccessLoggingInterceptor.class, BasicAuthInterceptor.class })
public class BookGrpcService extends BookServiceGrpc.BookServiceImplBase {

  private final BookService bookService;

  public BookGrpcService(BookService bookService) {
    this.bookService = bookService;
  }

  @Override
  public void addBook(Bookstore.AddBookRequest request, StreamObserver<Bookstore.Book> responseObserver) {
    Book newBook = new Book();
    newBook.setTitle(request.getTitle());
    newBook.setPublisher(request.getPublisher());
    newBook.setPublishedDate(TimestampConverter.fromProto(request.getPublishedDate()));

    Book savedBook = bookService.saveBook(newBook);
    responseObserver.onNext(Bookstore.Book.newBuilder()
      .setId(newBook.getId())
      .setTitle(newBook.getTitle())
      .setPublishedDate(TimestampConverter.toProto(savedBook.getPublishedDate()))
      .setPublisher(savedBook.getPublisher())
      .build());
    responseObserver.onCompleted();
  }

  @Override
  public void getBookDetails(Bookstore.GetBookDetailsRequest request, StreamObserver<Bookstore.Book> responseObserver) {
    Bookstore.Book bookDetail = bookService.findById(request.getBookId())
      .map(book -> Bookstore.Book.newBuilder()
        .setId(book.getId())
        .setTitle(book.getTitle())
        .setPublishedDate(TimestampConverter.toProto(book.getPublishedDate()))
        .setPublisher(book.getPublisher())
        .build())
      .orElseThrow();

    responseObserver.onNext(bookDetail);
    responseObserver.onCompleted();
  }

  @Override
  public void listBooks(Bookstore.ListBooksRequest request, StreamObserver<Bookstore.Book> responseObserver) {
    List<Book> books = bookService.findAll();

    for (Book book : books) {
      responseObserver.onNext(
        Bookstore.Book.newBuilder()
          .setId(book.getId())
          .setTitle(book.getTitle())
          .setPublishedDate(TimestampConverter.toProto(book.getPublishedDate()))
          .setPublisher(book.getPublisher())
          .build()
      );
    }

    responseObserver.onCompleted();
  }

  @Override
  public void searchBooksByAuthor(Bookstore.SearchBooksByAuthorRequest request, StreamObserver<Bookstore.Book> responseObserver) {
    List<Book> books = bookService.findByAuthorName(request.getAuthorName());

    for (Book book : books) {
      responseObserver.onNext(Bookstore.Book.newBuilder()
        .setId(book.getId())
        .setTitle(book.getTitle())
        .setPublishedDate(TimestampConverter.toProto(book.getPublishedDate()))
        .setPublisher(book.getPublisher())
        .build());
    }

    responseObserver.onCompleted();
  }
}

