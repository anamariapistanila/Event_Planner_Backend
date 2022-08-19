package licenta_backend.security;

public class JWTConstants {

    public static final long EXPIRATION_TIME = 86400000L; //1day
    public static final String SECRET_KEY =  "secret@Key";
    public static final String HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

}

