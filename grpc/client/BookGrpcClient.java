@Service
public class BookGrpcClient {
  @GrpcClient("local-grpc-server")
  private BookServiceGrpc.BookServiceBlockingStub bookServiceBlockingStub;

  public Bookstore.Book findById(final long bookId) {
    return this.bookServiceBlockingStub.getBookDetails(
      Bookstore.GetBookDetailsRequest
        .newBuilder()
        .setBookId(bookId)
        .build()
    );
  }

  public List<Bookstore.Book> findAll() {
    // Book list를 stream 타입으로 해서 Interator로 하나씩 받아와야함
    Iterator<Bookstore.Book> bookIterator = this.bookServiceBlockingStub.listBooks(
      Bookstore.ListBooksRequest
        .newBuilder()
        .build()
    );

    List<Bookstore.Book> bookList = new ArrayList<>();
    while(bookIterator.hasNext()) {
      bookList.add(bookIterator.next());
    }

    return bookList;
  }

}
