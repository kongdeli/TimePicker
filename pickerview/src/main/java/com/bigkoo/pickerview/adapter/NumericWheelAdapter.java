package com.bigkoo.pickerview.adapter;


import com.contrarywind.adapter.WheelAdapter;

/**
 * Numeric Wheel adapter.
 */
public class NumericWheelAdapter implements WheelAdapter {

    private int minValue;
    private int maxValue;
    private int minuteInterval;

    /**
     * Constructor
     *
     * @param minValue the wheel min value
     * @param maxValue the wheel max value
     */
    public NumericWheelAdapter(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public Object getItem(int index) {
        if (index >= 0 && index < getItemsCount()) {
            if (minuteInterval == 5) {
                return minValue + index * 5;
            } else {
                return minValue + index;
            }
        }
        return 0;
    }

    @Override
    public int getItemsCount() {
        if (minuteInterval == 5) {
            return (maxValue - minValue) / 5 + 1;
        }

        return maxValue - minValue + 1;
    }

    @Override
    public int indexOf(Object o) {
        try {
            return (int) o - minValue;
        } catch (Exception e) {
            return -1;
        }

    }

    public void setMinuteInterval(int minuteInterval) {
        this.minuteInterval = minuteInterval;
    }
}
