package decorators;

import interfaces.IText;

public abstract class TextDecorator implements IText {
    protected IText text;

    public TextDecorator(IText text){
        this.text = text;
    }

    @Override
    public String transform() {
        return text.transform();
    }

}
