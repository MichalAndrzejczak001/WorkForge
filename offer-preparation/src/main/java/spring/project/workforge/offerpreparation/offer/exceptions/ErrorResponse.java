package spring.project.workforge.offerpreparation.offer.exceptions;

public record ErrorResponse(
        int status,
        String message,
        String uri
) {}
