package not.nerds.secretsantaservice.api.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class PostResponse<T> extends ResponseEntity<T> {
    public PostResponse(HttpStatus status) {
        super(status);
    }

    public PostResponse(T body, HttpStatus status) {
        super(body, status);
    }

    public PostResponse(MultiValueMap<String, String> headers, HttpStatus status) {
        super(headers, status);
    }

    public PostResponse(T body, MultiValueMap<String, String> headers, HttpStatus status) {
        super(body, headers, status);
    }

    public PostResponse(T body, MultiValueMap<String, String> headers, int rawStatus) {
        super(body, headers, rawStatus);
    }
}
