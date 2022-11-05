package MOBLIMA.src;

public abstract class Menu {
    public Menu prevMenu;

    protected abstract void printMenu();

    protected void close(){
        if(prevMenu == null)
            return; //quit
        else{
            prevMenu.printMenu();
        }
    }

    protected void open(Menu currentMenu, Menu nextMenu){
        nextMenu.prevMenu = currentMenu;
        nextMenu.printMenu();
    }

    public Menu getPrevMenu(){
        return prevMenu;
    }
}
