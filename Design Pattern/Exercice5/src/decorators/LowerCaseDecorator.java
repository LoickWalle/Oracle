package decorators;

import interfaces.IText;

public class LowerCaseDecorator extends TextDecorator {
    public LowerCaseDecorator(IText text) {
        super(text);
    }

    @Override
    public String transform() {
        return text.transform().toLowerCase();
    }
}
