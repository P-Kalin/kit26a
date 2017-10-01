# �1 ����: ��������� �������� ����� Java. ���� �����, �������, �������� � ���������.<br>����: ������������ � JDK ��������� Java SE �� ����������� �������� Eclipse IDE.

# 1 ������������ ��������

## 1.1 ���������

������� ������� ������ ����������, ʲ�-26�, ������ 8.

## 1.2 ������

1. ������� ��� �������� ������ �� ��� Java � ���������� Eclipse.
2. ���������������� ��������� ��������� �������� �� ���������� ������ � ����� ������������, �� �������������� ��������� �� ������.
3. �������� ��������� � ������ �������� � ���������� ����� �� ��������� ��������� ����� JDK.

## 1.3 ��������

1. ������ ��� ������ �� ���������� �� ��������� �������� �� ������� �������� ��������:

  - �����, �� ������� ������ ������� ������ �� ��������� ���������������� �������;
  - �����, �� ������� ������ ��������� �������� (��������� � 380...) �� ��������� ����������� �������;
  - �����, ��� ���������� � ������� ���� ���������� ���� ������ ��������� �������� �� ��������� ��������� �������;
  - �����, ��� ���������� � ������� �������� ���������� ���� ������ ��������� �������� �� ��������� ��������� �������;
  - ��������� �������� �� ������� �������� ������� �� ������ �� 26 ���������� �� ������� ������ �������� � ������ �����;
  - ������ ����������� ������� � ��������� ������, ����� ����� ������� ���������� ����� ��������.

2. �������������� ���������� ����� �������������� �������� ����� ����� ������ � ���������� ������� ������ � �������� ����.

3. �������������� �������� ����� �������������� �������� ����� ����� ���������� ������� �������.

# 2 �������� ��������

## 2.1 ������ ���

ϳ� ��� �������� ���������� ������ ���� �������� �������� ������� �������� �� ������� ����, �������� ��� �������� � ������� ���� ��� ��������� ��������� �� ����������� ��������. ��� ��������� ��'���� ���������� ����� ���� ���������� ������� �������� (��������� ���. ���. 1), ����� ����� ���� Builder ��������� ��������� ���������� ����� �� ������ �������� ������� �����, ������� ���� ������� ����������� �� ���������. ���� LiteralBuilder ������� ���������� �� �������� ������, ��� ������������ ������������ �� ��� ���������.

## 2.2 �������� �� ��������� �����

����� �������� ������� ����� �������� �� ��������� ���������� ��� ������ �������� Eclipse - ObjectAid UML Diagram.

<center>
  <img src="http://i.piccy.info/i9/13e7d83f969ced57a2c84d90c6daacc9/1505352243/49031/1179545/ua_khpi_malokhvii01.png" alt="ĳ������ �����">
  <p>���. 1 - ĳ������� �����</p>
</center>

## 2.3 ���� ��������

�� ������ ����������� ������, ��������� ������� ���������� ������. �� �������� �� ���������� �������� ���������� �������� ���� ������� �� ��������� ����������� Debug � ���������� Eclipse.

## 2.4 ������ ��������� ��������

����� �������� �������� ��������� �������� ��� ���������� �������. ���� ��������� ��������� ��� �� ���������� (<https://sourceforge.net/p/kit26a-cpp/code/HEAD/tree/malokhvii_eduard/src/ua/khpi/malokhvii01/>).

```
package ua.khpi.malokhvii01;

import java.util.ArrayList;

public class TaskPerformerForDecimalValues {

    private ArrayList<Long> decimalNumbers;
    private TaskValues taskValues;

    public TaskPerformerForDecimalValues(final TaskValues taskValues) {
        this.decimalNumbers = new ArrayList<Long>();
        this.setTaskValues(taskValues);
    }

    public long countAmountOfPairedDigitsInNumber(final Long number) {
        long numberCopy = number;
        long amountOfPairedNumbers = 0;
        while (numberCopy != 0) {
            if (((numberCopy % 10) % 2) == 0) {
                ++amountOfPairedNumbers;
            }
            numberCopy /= 10;
        }

        return amountOfPairedNumbers;
    }

    public long countAmountOfPairedDigitsInNumbers() {
        long amountOfPairedDigits = 0;
        for (Long number : this.decimalNumbers) {
            amountOfPairedDigits += this
                    .countAmountOfPairedDigitsInNumber(number);
        }

        return amountOfPairedDigits;
    }

    public long countAmountOfUnpairedDigitsInNumber(final Long number) {
        long numberCopy = number;
        long amountOfUnpairedNumbers = 0;
        while (numberCopy != 0) {
            if (((numberCopy % 10) % 2) != 0) {
                ++amountOfUnpairedNumbers;
            }
            numberCopy /= 10;
        }

        return amountOfUnpairedNumbers;
    }

    public long countAmountOfUnpairedDigitsInNumbers() {
        long amountOfUnpairedDigits = 0;
        for (Long number : this.decimalNumbers) {
            amountOfUnpairedDigits += this
                    .countAmountOfUnpairedDigitsInNumber(number);
        }

        return amountOfUnpairedDigits;
    }

    public TaskValues getTaskInitialValues() {
        return this.taskValues;
    }

    public void setTaskValues(final TaskValues taskValues) {
        this.taskValues = taskValues;

        this.decimalNumbers.clear();
        this.decimalNumbers
                .add(new Long(this.taskValues.getMobilePhoneNumber()));
        this.decimalNumbers.add(new Long(
                this.taskValues.getLastTwoDigitsOfMobilePhoneNumber()));
        this.decimalNumbers.add(new Long(
                this.taskValues.getLastFourDigitsOfMobilePhoneNumber()));

        this.decimalNumbers
                .add(new Long(this.taskValues.getRecordBookNumber()));
        this.decimalNumbers
                .add(new Long(this.taskValues.getEnglishUpperCaseLetter()));
    }
}
```

# 3 ���������� ������

����� �������� ��������� ��������� �������� �� ���������� ������ � ����� ������������, �� �������������� ��������� �� ������, ����� ���������� ��������.

<center>
  <img src="http://i.piccy.info/i9/33ae3dff168d6d418550f0c83ac65ff1/1505352267/110052/1179545/debug.png" alt="������������ ����������� Debug">
  <p>���. 2 - ������������ ����������� Debug</p>
</center>

<center>
  <img src="http://i.piccy.info/i9/a04e3954ac2a37d7bd5e5bc859fd7954/1505352204/23871/1179545/variables.png" alt="������������ ����������� ������">
  <p>���. 3 - ������������ ��������� �������</p>
</center>

# ��������

� ��� ��������� ����������� ������ ���� �������� ����� �������, �� �������� ������������ ������ �������� Eclipse. ������������ � ���������� Java SE, ��������� ���� �����, ��������.
