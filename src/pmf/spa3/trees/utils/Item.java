package pmf.spa3.trees.utils;

public class Item {
    
    private char id;
    private String action;
    private Item nextAction, subMenu;
    
    public Item() {
    }

    public char getId() {
        return id;
    }

    public void setId(char id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Item getNextAction() {
        return nextAction;
    }

    public void setNextAction(Item nextAction) {
        this.nextAction = nextAction;
    }

    public Item getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(Item subMenu) {
        this.subMenu = subMenu;
    } 

    
}
