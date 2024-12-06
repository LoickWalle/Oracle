import interfaces.ICommand;

public class RemoteControl {
    private ICommand command1;
    private ICommand command2;
    private boolean isOn;

    public RemoteControl(ICommand command1, ICommand command2) {
        this.command1 = command1;
        this.command2 = command2;
        this.isOn = false;
    }

    public void pressButton(){
        if(command1 != null && command2 != null){
            if (isOn) {
                command1.execute();
            } else {
                command2.execute();
            }

            isOn = !isOn;
        }else {
            System.out.println("Aucune commande assigné à ce bouton.");
        }
    }
}
