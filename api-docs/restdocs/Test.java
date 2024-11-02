@RequiredArgsConstructor
@AutoConfigureRestDocs
@AutoConfigureMockMvc
@Import(RestDocsConfig.class)
@TestConstructor(autowireMode = ALL)
@SpringBootTest
public class BookControllerTest {
    private final MockMvc mockMvc;

    @Test
    void getBookById() throws Exception {
        mockMvc.perform(
                        RestDocumentationRequestBuilders.get("/books/1")
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("getBookById",
                // 모든 결과에 대한 테스트 작성
                // 하나라도 틀릴시 컴파일 에러
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("책 번호"),
                                fieldWithPath("title").type(JsonFieldType.STRING).description("타이틀"),
                                fieldWithPath("publisher").type(JsonFieldType.STRING).description("출판사"),
                                fieldWithPath("publishedDate").type(JsonFieldType.STRING).description("출판일시"),
                                fieldWithPath("authors").type(JsonFieldType.ARRAY).description("저자 리스트"),
                                fieldWithPath("authors.[].id").type(JsonFieldType.NUMBER).description("저자 아이디"),
                                fieldWithPath("authors.[].name").type(JsonFieldType.STRING).description("저자 이름"),
                                fieldWithPath("reviews").type(JsonFieldType.ARRAY).description("리뷰 리스트"),
                                fieldWithPath("reviews.[].id").type(JsonFieldType.NUMBER).description("리뷰 번호"),
                                fieldWithPath("reviews.[].content").type(JsonFieldType.STRING).description("리뷰 내용"),
                                fieldWithPath("reviews.[].rating").type(JsonFieldType.NUMBER).description("리뷰 평점"),
                                fieldWithPath("reviews.[].createdDate").type(JsonFieldType.STRING).description("리뷰 일시")
                        )
                ))
                .andDo(print())
        ;
    }

    @Test
    void getBooks() throws Exception {
        mockMvc.perform(
                        RestDocumentationRequestBuilders.get("/books")
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("getBooks",
                        responseFields(
                                fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("책 번호"),
                                fieldWithPath("[].title").type(JsonFieldType.STRING).description("타이틀"),
                                fieldWithPath("[].publisher").type(JsonFieldType.STRING).description("출판사"),
                                fieldWithPath("[].publishedDate").type(JsonFieldType.STRING).description("출판일시"),
                                fieldWithPath("[].authors").type(JsonFieldType.ARRAY).description("저자 리스트"),
                                fieldWithPath("[].authors.[].id").type(JsonFieldType.NUMBER).description("저자 아이디"),
                                fieldWithPath("[].authors.[].name").type(JsonFieldType.STRING).description("저자 이름"),
                                fieldWithPath("[].reviews").type(JsonFieldType.ARRAY).description("리뷰 리스트"),
                                fieldWithPath("[].reviews.[].id").type(JsonFieldType.NUMBER).description("리뷰 번호"),
                                fieldWithPath("[].reviews.[].content").type(JsonFieldType.STRING).description("리뷰 내용"),
                                fieldWithPath("[].reviews.[].rating").type(JsonFieldType.NUMBER).description("리뷰 평점"),
                                fieldWithPath("[].reviews.[].createdDate").type(JsonFieldType.STRING).description("리뷰 일시")
                        )
                ))
                .andDo(print())
        ;
    }
}