package com.company.item;

/*aici am folosit factory combinat cu builder
* pentru ca nu toate itemele incrementeaza toate
* atributele*/

/*de asemenea, este mai usor de adaugat un item nou*/
public class ItemFactory {
    private static ItemFactory instantaFactory;

    private ItemFactory() {}

    public static ItemFactory getInstanta() {
        if (instantaFactory == null) {
           instantaFactory = new ItemFactory();
        }
        return instantaFactory;
    }

    public Item createItem(String name) {
        switch (name) {
            case "Scut":
                return new ItemBuilder()
                        .withName(name)
                        .withIncDef(2)
                        .withIncSpecialDef(2)
                        .build();
            case "Vesta":
                return new ItemBuilder()
                        .withName(name)
                        .withIncHp(10)
                        .build();
            case "Sabiuta":
                return new ItemBuilder()
                        .withName(name)
                        .withIncAttack(3)
                        .build();
            case "Bagheta Magica":
                return new ItemBuilder()
                        .withName(name)
                        .withIncSpecialAttack(3)
                        .build();
            case "Vitamine":
                return new ItemBuilder()
                        .withName(name)
                        .withIncHp(2)
                        .withIncAttack(2)
                        .withIncSpecialAttack(2)
                        .build();
            case "Brad de craciun":
                return new ItemBuilder()
                        .withName(name)
                        .withIncAttack(3)
                        .withIncDef(1)
                        .build();
            case "Pelerina":
                return new ItemBuilder()
                        .withName(name)
                        .withIncSpecialDef(3)
                        .build();
        } throw new IllegalArgumentException("Item-ul " + name + " nu exista!");
    }
}
