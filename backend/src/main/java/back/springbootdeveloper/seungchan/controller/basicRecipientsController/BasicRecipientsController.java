package back.springbootdeveloper.seungchan.controller.basicRecipientsController;

import back.springbootdeveloper.seungchan.domain.Article;
import back.springbootdeveloper.seungchan.domain.Recipients;
import back.springbootdeveloper.seungchan.dto.EachArticleResponseDTO;
import back.springbootdeveloper.seungchan.dto.basicrecipientsDTO.BasicRecipientsArticleResponse;
import back.springbootdeveloper.seungchan.dto.basicrecipientsDTO.EachArticleOfBasicRecipientResponseDTO;
import back.springbootdeveloper.seungchan.service.basicRecipientsService.BasicRecipientsService;
import back.springbootdeveloper.seungchan.service.oldmansDisablesService.OldmansDisablesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor // final 이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 룸복 어노테이션
@RestController
public class BasicRecipientsController {
    private final BasicRecipientsService basicRecipientsService;

    @GetMapping("/article/basic-recipients")
    // 글 전체를 조회 -> 응답용 객체인 ArticleResponse로 파싱 -> body에 담아 클라이언트에게 전송
    public ResponseEntity<List<BasicRecipientsArticleResponse>> findAllArticles() {
        // responseEntity -> List -> BasicRecipientsResponse

        List<BasicRecipientsArticleResponse> articles = basicRecipientsService.findAll()
                .stream()
                // blogService에서 찾아온 Article의 하나하나가 파라미터로 넘어간다.
                .map(BasicRecipientsArticleResponse::new) // list -> BasicRecipientsResponse new
                .toList();

        return ResponseEntity.ok().body(articles);
    }

    // 박승찬
    @GetMapping("/article/basic-recipients/{id}")
    // @PathVariable 매너테이션은 URL에서 값을 가져오는 애너테이션
    // /api/articles/3 GET 요청을 받으면 id에 3이 들어온다.
    public ResponseEntity<EachArticleOfBasicRecipientResponseDTO> findArticleById(@PathVariable long id) {
        Recipients article = basicRecipientsService.findById(id);

        return ResponseEntity.ok()
                .body(new EachArticleOfBasicRecipientResponseDTO(article));
    }
}
