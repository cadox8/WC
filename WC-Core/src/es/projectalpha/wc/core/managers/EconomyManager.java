package es.projectalpha.wc.core.managers;

import es.projectalpha.wc.core.api.WCUser;

public class EconomyManager {

    private WCUser user;
    private DataManager dataManager;

    public EconomyManager(WCUser user){
        this.user = user;
        this.dataManager = new DataManager(user);
    }

    public boolean addMoney(double amount){
        return dataManager.setObject("money", dataManager.getMoney() + amount);
    }

    public boolean removeMoney(double amount){
        if (dataManager.getMoney() - amount <= 0){
            return dataManager.setObject("money", 0);
        }
        return dataManager.setObject("money", dataManager.getMoney() - amount);
    }
}
