package not.nerds.secretsantaservice.api.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class GetResponse<T> extends ResponseEntity {
    public GetResponse(HttpStatus status) {
        super(status);
    }

    public GetResponse(Object body, HttpStatus status) {
        super(body, status);
    }

    public GetResponse(MultiValueMap headers, HttpStatus status) {
        super(headers, status);
    }

    public GetResponse(Object body, MultiValueMap headers, HttpStatus status) {
        super(body, headers, status);
    }

    public GetResponse(Object body, MultiValueMap headers, int rawStatus) {
        super(body, headers, rawStatus);
    }
}
