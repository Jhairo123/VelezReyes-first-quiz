package org.velezreyes.quiz.question6;

import java.util.HashMap;
import java.util.Map;

public class VendingMachineImpl implements VendingMachine {
    private Map<String, Integer> drinkPrices;
    private int balance;

    public VendingMachineImpl() {
        drinkPrices = new HashMap<>();
        drinkPrices.put("ScottCola", 75);
        drinkPrices.put("KarenTea", 100);
    }

    public static VendingMachine getInstance() {
        return new VendingMachineImpl();
    }

    @Override
    public void insertQuarter() {
        balance += 25;
    }

    @Override
    public Drink pressButton(String name) throws NotEnoughMoneyException, UnknownDrinkException {
        if (drinkPrices.containsKey(name)) {
            int price = drinkPrices.get(name);
            if (balance >= price) {
                balance -= price;
                if ("ScottCola".equals(name)) {
                    return new Drink() {
                        @Override
                        public String getName() {
                            return "ScottCola";
                        }

                        @Override
                        public boolean isFizzy() {
                            return true;
                        }
                    };
                } else if ("KarenTea".equals(name)) {
                    return new Drink() {
                        @Override
                        public String getName() {
                            return "KarenTea";
                        }

                        @Override
                        public boolean isFizzy() {
                            return false;
                        }
                    };
                }
            } else {
                throw new NotEnoughMoneyException();
            }
        } else {
            throw new UnknownDrinkException();
        }

        return null;
    }
}
