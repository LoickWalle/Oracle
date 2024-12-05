import decorators.PrefixDecorator;
import decorators.SuffixDecorator;
import decorators.UpperCaseDecorator;
import decorators.LowerCaseDecorator;
import interfaces.IText;
import models.Text;

public class Main {
    public static void main(String[] args) {
        IText text = new Text("Polo");
        System.out.println(text.transform());

        IText upperText = new UpperCaseDecorator(text);
        System.out.println(upperText.transform());

        IText lowerText = new LowerCaseDecorator(upperText);
        System.out.println(lowerText.transform());

        IText prefixText = new PrefixDecorator(lowerText, "PO");
        System.out.println(prefixText.transform());

        IText suffixText = new SuffixDecorator(prefixText, "LO");
        System.out.println(suffixText.transform());
     }
}