package com.company.item;

/*o clasa simpla pentru a emula un item*/

/*un item se poate obtine doar prin
* intermediul lui ItemFactory*/
public class Item implements Cloneable {
    private String name;
    private Integer incHp = 0;
    private Integer incAttack = 0;
    private Integer incSpecialAttack = 0;
    private Integer incDefense = 0;
    private Integer incSpecialDefense = 0;

    public void setName(String name) {
        this.name = name;
    }

    public void setIncHp(Integer incHp) {
        this.incHp = incHp;
    }

    public void setIncAttack(Integer incAttack) {
        this.incAttack = incAttack;
    }

    public void setIncSpecialAttack(Integer incSpecialAttack) {
        this.incSpecialAttack = incSpecialAttack;
    }

    public void setIncDefense(Integer incDefense) {
        this.incDefense = incDefense;
    }

    public void setIncSpecialDefense(Integer incSpecialDefense) {
        this.incSpecialDefense = incSpecialDefense;
    }

    public String getName() {
        return name;
    }

    public Integer getIncHp() {
        return incHp;
    }

    public Integer getIncAttack() {
        return incAttack;
    }

    public Integer getIncSpecialAttack() {
        return incSpecialAttack;
    }

    public Integer getIncDefense() {
        return incDefense;
    }

    public Integer getIncSpecialDefense() {
        return incSpecialDefense;
    }

    public Item clone() {
        return ItemFactory.getInstanta().createItem(this.name);
    }
}
