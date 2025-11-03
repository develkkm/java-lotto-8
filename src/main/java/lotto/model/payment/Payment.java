package lotto.model.payment;

public class Payment {
    public static final int LOTTO_PRICE = 1000;

    private final int money;

    private Payment(int money){
        validateAmount(money);
        this.money = money;
    }

    public static Payment from(int money){
        return new Payment(money);
    }

    private void validateAmount(int money){
        validateMoneyRange(money);
        validateDivideByLottoPrice(money);
    }

    private void validateMoneyRange(int money) {
        if(money < LOTTO_PRICE) {
            throw new IllegalArgumentException("금액은 " + LOTTO_PRICE + "원 이상이여야 합니다.");
        }
    }

    private void validateDivideByLottoPrice(int money) {
        if(money % LOTTO_PRICE != 0){
            throw new IllegalArgumentException("금액은" + LOTTO_PRICE + "원 단위로 나누어져야합니다.");
        }
    }

    public int getLottoAmount(){
        return money / LOTTO_PRICE;
    }

    public int getMoney(){
        return money;
    }
}
