package decorators;

import interfaces.IText;

public class UpperCaseDecorator extends TextDecorator {
    public UpperCaseDecorator(IText text) {
        super(text);
    }

    @Override
    public String transform() {
        return text.transform().toUpperCase();
    }
}
