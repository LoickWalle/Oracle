package decorators;

import interfaces.IText;

public class
































































































































PrefixDecorator extends TextDecorator{

    String prefix;

    public PrefixDecorator(IText text, String prefix) {
        super(text);
        this.prefix = prefix;
    }

    @Override
    public String transform() {
        return prefix + text.transform();
    }
}
