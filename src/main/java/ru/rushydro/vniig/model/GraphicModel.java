package ru.rushydro.vniig.model;

import ru.rushydro.vniig.util.data.DateConverter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by nikolay on 22.11.15.
 */
public class GraphicModel {

    List<GraphicItem> items = new ArrayList<>();

    double minY;

    double maxY;

    String startDay;

    String endDay;

    public List<GraphicItem> getItems() {
        return items;
    }

    public void setItems(List<GraphicItem> items) {
        this.items = items;
    }

    public double getMinY() {
        return minY;
    }

    public void setMinY(double minY) {
        this.minY = minY;
    }

    public double getMaxY() {
        return maxY;
    }

    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public String toJSON() {

        return "[" + items.stream().map(GraphicItem::toJSON).collect(Collectors.joining(",")) + "]";
    }

    public Long getMinX() {
        Date startDate = null;
        if (startDay != null && !startDay.isEmpty()) {
            try {
                startDate = DateConverter.parse(startDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        Calendar calendar = Calendar.getInstance();

        if (startDate == null) {
            Double min = items.stream().mapToDouble(item -> item.getPoints().stream().mapToDouble(GraphicPoint::getX).min().getAsDouble()).min().getAsDouble();
            calendar.setTimeInMillis(min.longValue());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            startDate = calendar.getTime();
        }

        return startDate.getTime();
    }

    public Long getMaxX() {
        Date endDate = null;
        if (endDay != null && !endDay.isEmpty()) {
            try {
                endDate = DateConverter.parse(endDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        Calendar calendar = Calendar.getInstance();

        if (endDate == null) {
            Double max = items.stream().mapToDouble(item -> item.getPoints().stream().mapToDouble(GraphicPoint::getX).max().getAsDouble()).max().getAsDouble();
            calendar.setTimeInMillis(max.longValue());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            endDate = calendar.getTime();

        }

        calendar.setTime(endDate);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        endDate = calendar.getTime();

        return endDate.getTime();
    }

    public String getTicks() {
        List<Date> dateList = new ArrayList<>();

        Date startDate = null;
        Date endDate = null;
        if (startDay != null && !startDay.isEmpty()) {
            try {
                startDate = DateConverter.parse(startDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (endDay != null && !endDay.isEmpty()) {
            try {
                endDate = DateConverter.parse(endDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        Calendar calendar = Calendar.getInstance();

        if (startDate == null) {
            Double min = items.stream().mapToDouble(item -> item.getPoints().stream().mapToDouble(GraphicPoint::getX).min().getAsDouble()).min().getAsDouble();
            calendar.setTimeInMillis(min.longValue());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            startDate = calendar.getTime();
        }

        if (endDate == null) {
            Double max = items.stream().mapToDouble(item -> item.getPoints().stream().mapToDouble(GraphicPoint::getX).max().getAsDouble()).max().getAsDouble();
            calendar.setTimeInMillis(max.longValue());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            endDate = calendar.getTime();

        }

        calendar.setTime(endDate);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        endDate = calendar.getTime();
        int end = calendar.get(Calendar.DAY_OF_YEAR);

        calendar.setTime(startDate);
        int start = calendar.get(Calendar.DAY_OF_YEAR);

        if(end < start) {
            end += 365;
        }

        if (end - start < 5) {
            calendar.setTime(startDate);
            dateList.add(calendar.getTime());
            for (int i = start; i <= end; i++) {
                calendar.add(Calendar.DAY_OF_YEAR, 1);
                dateList.add(calendar.getTime());
            }
        } else {
            int first = (end - start) / 2;
            int second = first / 2;
            int third = first + second;

            dateList.add(startDate);
            calendar.setTime(startDate);
            calendar.add(Calendar.DAY_OF_YEAR, second);
            dateList.add(calendar.getTime());
            calendar.setTime(startDate);
            calendar.add(Calendar.DAY_OF_YEAR, first);
            dateList.add(calendar.getTime());
            calendar.setTime(startDate);
            calendar.add(Calendar.DAY_OF_YEAR, third);
            dateList.add(calendar.getTime());
            dateList.add(endDate);
        }

        return "[" + dateList.stream().map(date -> "[" + date.getTime() + ",'" + DateConverter.format(date) + "']").collect(Collectors.joining(",")) + "]";
    }
}
