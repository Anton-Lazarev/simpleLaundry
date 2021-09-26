package com.company;

public class Main {

    public static void main(String[] args) {
        int[] orders = {4, 6, 9, 5, 3, 43, 6, 33, 66, 4, 7, 3, 66, 4, 3, 5, 31, 98, 12, 47, 2, 6, 14, 45};
        int washers = 6;
        int workedTime = workingLaundry(orders, washers);
        System.out.println("Summary working time of laundry: " + workedTime);
    }

    public static int workingLaundry(int[] allOrders, int allWashMachines) {
        //Если на вход подаётся некооректное или нулевое число работающих стиральных машин
        if (allWashMachines <= 0) {
            System.out.println("Стиралки все сломались");
            return 0;
        } //Если на вход подаётся лишь одна стиральная машина, то все заказы выполнятся последовательно
        else if (allWashMachines == 1) {
            int workingTime = 0;
            for (int orderTime : allOrders) {
                workingTime += orderTime;
            }
            return workingTime;
        } //Если количество стиралок больше или равно количеству заказов, то нам нужен только максимум из заказов
        else if (allOrders.length <= allWashMachines) {
            int workingTime = 0;
            for (int orderTime : allOrders) {
                if (workingTime <= orderTime) workingTime = orderTime;
            }
            return workingTime;
        } //В остальных случаях общее время работы прачечной будет максимумом из времени работы стиралки, посчитанной для каждой отдельно
        else {
            int laundryWorkingTime = 0;
            int[] workingTimeEachMachine = new int[allWashMachines];

            for (int i = 0; i < allOrders.length; i++) {
                int minOrderTime = 99999999;
                //Находим минимальное время среди стиралок, т.к. к нему и будет прибавлен следующий заказ
                for (int currentWasherTime : workingTimeEachMachine)
                    if (minOrderTime >= currentWasherTime) minOrderTime = currentWasherTime;
                //Находим первый минимальный элемент среди стиралок и к нему прибавляем заказ, выходим из цикла, чтоб не изменить лишнего (если есть повторы минимума)
                for (int j = 0; j < allWashMachines; j++) {
                    if (workingTimeEachMachine[j] == minOrderTime) {
                        workingTimeEachMachine[j] = workingTimeEachMachine[j] + allOrders[i];
                        break;
                    }
                }
            }
            for (int workedTime : workingTimeEachMachine) {
                if (workedTime >= laundryWorkingTime) laundryWorkingTime = workedTime;
            }
            return laundryWorkingTime;
        }
    }
}
