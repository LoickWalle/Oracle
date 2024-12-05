package decorators;

import interfaces.IText;

public class SuffixDecorator extends TextDecorator{
    String suffix;

    public SuffixDecorator(IText text, String suffix) {
        super(text);
        this.suffix = suffix;
    }

    @Override
    public String transform() {
        return text.transform() + suffix;
    }
}
