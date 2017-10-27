package io.tatagulov.goodproject.web.api;

import java.util.Locale;

public class WebContext implements Context {
    private final Locale locale;

    public WebContext(Locale locale) {
        this.locale = locale;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }
}
