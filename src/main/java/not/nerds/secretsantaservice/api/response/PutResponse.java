package not.nerds.secretsantaservice.api.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class PutResponse<T> extends ResponseEntity<T> {
    public PutResponse(HttpStatus status) {
        super(status);
    }

    public PutResponse(T body, HttpStatus status) {
        super(body, status);
    }

    public PutResponse(MultiValueMap<String, String> headers, HttpStatus status) {
        super(headers, status);
    }

    public PutResponse(T body, MultiValueMap<String, String> headers, HttpStatus status) {
        super(body, headers, status);
    }

    public PutResponse(T body, MultiValueMap<String, String> headers, int rawStatus) {
        super(body, headers, rawStatus);
    }
}
