package ru.rushydro.vniig.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.rushydro.vniig.entry.Criterion;
import ru.rushydro.vniig.entry.Sensor;
import ru.rushydro.vniig.entry.SensorValue;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
public class DataService {

    public static double SQUERE = 19.3;

    public void buildDataNew(Model model) {
        final List<Long> notInSensorIds = new ArrayList<>();

        notInSensorIds.addAll(LongStream.range(7, 47).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(59, 86).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(92, 100).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(110, 118).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(127, 132).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(144, 152).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(162, 170).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(196, 204).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(214, 223).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(247, 257).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(265, 276).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(298, 311).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(314, 328).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(350, 364).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(366, 381).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(401, 416).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(430, 433).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(453, 456).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(1899, 1901).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(1949, 1956).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(1998, 2011).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(2047, 2066).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(2094, 2121).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(2143, 2176).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(2193, 2231).boxed().collect(Collectors.toList()));
        notInSensorIds.addAll(LongStream.range(2240, 2286).boxed().collect(Collectors.toList()));

        List<Sensor> sensors = new ArrayList<>();
        BigDecimal start = new BigDecimal("0");

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        double y = 228;
        double x = 180;

        int k = -1;

        List<Criterion> criterions = new ArrayList<>();

        int b = 255;

        int r = 0;

        for (int i = 0; i < 50; i++) {
            Criterion criterion = new Criterion();
            criterion.setId(i + 1);
            criterion.setStartValue(i != 0 ? i : -999999999);
            criterion.setEndValue(i != 49 ? i+1 : 999999999);
            criterion.setBlue(b);
            criterion.setRed(r);
            b -= 5;
            r += 5;
            criterions.add(criterion);
        }

        Map<Long, SensorValue> sensorValues = new HashMap<>();

        int id = 1;

        for (int i = 1; i <= 2288; i++) {

            Sensor sensor = new Sensor();
            sensor.setId(id++);
            sensor.setX(x);
            sensor.setY(y);

            if (i % 26 == 0) {
                k = -1 * k;
                x += SQUERE;
            } else {
                y -= k * SQUERE;
            }

            sensor.setLengthValue("" + start.toString());
            start = start.add(new BigDecimal("0.2"));

            if (!notInSensorIds.contains(sensor.getId())) {
                sensors.add(sensor);
            }
        }

        x = sensors.get(sensors.size() - 7).getX();
        y = sensors.get(sensors.size() - 7).getY() - SQUERE;
        sensors.add(createSensor(id++, x,y));
        x += SQUERE;
        sensors.add(createSensor(id++, x,y));
        x += SQUERE;
        sensors.add(createSensor(id++, x,y));
        x += SQUERE;
        sensors.add(createSensor(id++, x,y));

        x += SQUERE;
        y -= 1.5 * SQUERE;
        Sensor sensorCustom = createSensor(id++, x,y);
        sensorCustom.setHeight(2.5 * SQUERE);
        sensorCustom.setWidth(1.5 * SQUERE);
        sensors.add(sensorCustom);

        x += 1.5 * SQUERE;
        sensors.add(createSensor(id, x,y));

        int j = 0;

        List<Sensor> top = sensors.stream()
                .filter(s -> (s.getX() > 320 && s.getY() < 460) || (s.getX() < 320 && s.getY() < 450))
                .sorted((s1, s2) -> {
                    int o = (int) (s2.getX() - s1.getX());
                    if (o != 0) {
                        return o;
                    }
                    if (s1.getX() > 390) {
                        return Math.ceil(s1.getX() / SQUERE) % 2 == 0 ? (int) (s2.getY() - s1.getY()) : (int) (s1.getY() - s2.getY()) ;
                    } else {
                        return Math.ceil(s1.getX() / SQUERE) % 2 == 0 ? (int) (s1.getY() - s2.getY()) : (int) (s2.getY() - s1.getY()) ;
                    }
                })
                .collect(Collectors.toList());

        id = 1;

        for (Sensor sensor : top) {
            if (sensor.getX() > 190 && sensor.getX() < 200) {
                sensor.setX(sensor.getX() + 4);
                sensor.setWidth(sensor.getWidth() - 4);
            }
            if (sensor.getX() < 190) {
                sensor.setX(sensor.getX() + 11);
                sensor.setWidth(sensor.getWidth() - 7);
            }
        }

        x = top.get(top.size() - 1).getX() - SQUERE;
        y = top.get(top.size() - 1).getY();
        sensorCustom = createSensor(999999, x, y);
        sensors.add(sensorCustom);
        top.add(sensorCustom);

        y -= SQUERE;
        sensorCustom = createSensor(999999, x, y);
        sensors.add(sensorCustom);
        top.add(sensorCustom);

        y -= SQUERE;
        sensorCustom = createSensor(999999, x, y);
        sensors.add(sensorCustom);
        top.add(sensorCustom);

        y -= SQUERE;
        sensorCustom = createSensor(999999, x, y);
        sensors.add(sensorCustom);
        top.add(sensorCustom);

        y -= SQUERE;
        sensorCustom = createSensor(999999, x, y);
        sensors.add(sensorCustom);
        top.add(sensorCustom);

        for (Sensor sensor : top) {
            sensor.setId(id++);
        }

        List<Sensor> bottom = sensors.stream()
                .filter(s -> (s.getX() > 320 && s.getY() > 460)  || (s.getX() < 320 && s.getY() > 450))
                .sorted((s1, s2) -> {
                    int o = (int) (s2.getX() - s1.getX());
                    if (o != 0) {
                        return o;
                    }
                    return Math.ceil(s1.getX() / SQUERE) % 2 == 0 ?(int) (s1.getY() - s2.getY()) :  (int) (s2.getY() - s1.getY());
                })
                .collect(Collectors.toList());

        for (Sensor sensor : bottom) {
            sensor.setId(id++);
        }

        for (Sensor sensor : sensors) {
            SensorValue value = new SensorValue();
            value.setId(sensor.getId());
            value.setValue(6);
            value.setDate(date);
            value.setTime(time);
            value.setColor(criterions.get(7).getColor());

            sensorValues.put(value.getId(), value);
        }

        List<Sensor> block1 = top.stream().filter(s -> s.getX() < 1100).collect(Collectors.toList());
        List<Sensor> block2 = top.stream().filter(s -> s.getX() > 1100).collect(Collectors.toList());
        List<Sensor> block3 = bottom.stream().filter(s -> s.getX() < 990).collect(Collectors.toList());
        List<Sensor> block4 = bottom.stream().filter(s -> s.getX() > 990).collect(Collectors.toList());

        Sensor prev = block1.get(0);

        prev.setStartLengthValue(new BigDecimal("73"));
        prev.setEndLengthValue(new BigDecimal("74"));
        prev.setStartFileLengthValue(new BigDecimal("30"));
        prev.setEndFileLengthValue(new BigDecimal("30"));

        processBlock(block1, 1, prev, 1, 1.015);

        id = (int) block4.get(0).getId();

        block4.sort((s1,s2) -> Long.compareUnsigned(s2.getId(), s1.getId()));

        for (Sensor sensor : block4) {
            sensor.setId(id++);
        }

        prev = block4.get(0);

        prev.setStartLengthValue(new BigDecimal(1299.5));
        prev.setEndLengthValue(new BigDecimal(1298));
        prev.setStartFileLengthValue(new BigDecimal(350));
        prev.setEndFileLengthValue(new BigDecimal(351.5));

        processBlock4(block4, prev);

        block3.sort((s1, s2) -> {
            if (s1.getX() < 250 && s2.getX() < 250) {
                return (int) (s2.getId() - s1.getId());
            } else {
                return (int) (s1.getId() - s2.getId());
            }
        });

        prev = block3.get(0);

        prev.setStartLengthValue(new BigDecimal(133.5));
        prev.setEndLengthValue(new BigDecimal(135));
        prev.setStartFileLengthValue(new BigDecimal(350));
        prev.setEndFileLengthValue(new BigDecimal(351.5));

        processBlock(block3, 3, prev, 1, 1.015);

        for (Sensor s : block3) {
            if (s.getX() > 250 && s.getX() < 260 && s.getY() > 590 && s.getY() < 600) {
                s.setStartLengthValue(new BigDecimal(1250));
                s.setEndLengthValue(new BigDecimal(1253.5));
                s.setStartFileLengthValue(new BigDecimal(1483.2));
                s.setEndFileLengthValue(new BigDecimal(1486.8));
            } else if (s.getX() > 250 && s.getX() < 260 && s.getY() > 610 && s.getY() < 620) {
                s.setStartLengthValue(new BigDecimal(1253.5));
                s.setEndLengthValue(new BigDecimal(1259));
                s.setStartFileLengthValue(new BigDecimal(1486.8));
                s.setEndFileLengthValue(new BigDecimal(1492.4));
            }
        }

        prev = block2.get(0);

        prev.setStartLengthValue(new BigDecimal(50));
        prev.setEndLengthValue(new BigDecimal(52));
        prev.setStartFileLengthValue(new BigDecimal(1267.6));
        prev.setEndFileLengthValue(new BigDecimal(1265.5));

        processBlock2(block2, 2, prev, 1, 1.015);

        sensors.sort(Comparator.comparingLong(Sensor::getId));

        model.addAttribute("sensors", sensors);
        model.addAttribute("sensorValues", sensorValues);
        model.addAttribute("minValue", sensorValues.values().stream().min((v1,v2) -> (int) (v1.getValue() - v2.getValue())).get().getValue() - 1);
        model.addAttribute("maxValue", sensorValues.values().stream().max((v1,v2) -> (int) (v1.getValue() - v2.getValue())).get().getValue() + 1);

        buildInsert(sensors, sensorValues);
    }

    private void buildInsert(List<Sensor> sensors, Map<Long, SensorValue> sensorValues) {
        try (FileOutputStream fos = new FileOutputStream("C:/work/data.sql");
             OutputStreamWriter osw = new OutputStreamWriter(fos);
             BufferedWriter bw = new BufferedWriter(osw)) {
            sensors.forEach(s -> {
                try {
                    bw.write(String.format("insert into sensor(id, start_length_value, end_length_value, " +
                                    "start_file_length_value, end_file_length_value, x, y, width, height) " +
                                    "values(%s, %s, %s, %s, %s, %s, %s, %s, %s);", s.getId(), s.getStartLengthValue(), s.getEndLengthValue(),
                            s.getStartFileLengthValue(), s.getEndFileLengthValue(), s.getX(), s.getY(),
                            s.getWidth(), s.getHeight()));
                    bw.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            bw.newLine();
            bw.newLine();

            sensorValues.values().forEach(sv -> {
                try {
                    bw.write("insert into sensor_value values (" +
                            sv.getId() + "," + sv.getId() + "," + sv.getValue() + ",CURRENT_DATE,CURRENT_TIME" +");");
                    bw.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            bw.newLine();
            bw.newLine();

            sensorValues.values().forEach(sv -> {
                try {
                    bw.write("insert into sensor_value_storage values (" +
                            sv.getId() + "," + sv.getId() + "," + sv.getValue() + ",CURRENT_DATE,CURRENT_TIME" +");");
                    bw.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processBlock(List<Sensor> block, int blockNumber, Sensor prev, int start, double k) {
        int o = 1;

        for (int c = start; c < block.size(); c++ ) {
            Sensor current = block.get(c);
            if (isWithLength(blockNumber, current)) {
                o++;
                current.setStartLengthValue(prev.getEndLengthValue().setScale(2, RoundingMode.HALF_UP));
                current.setEndLengthValue(current.getStartLengthValue().add(getLength(blockNumber, o)).setScale(2, RoundingMode.HALF_UP));
                current.setStartFileLengthValue(prev.getEndFileLengthValue().setScale(4, RoundingMode.HALF_UP));
                current.setEndFileLengthValue(current.getEndLengthValue().subtract(current.getStartLengthValue())
                        .multiply(new BigDecimal(k)).add(current.getStartFileLengthValue()).setScale(4, RoundingMode.HALF_UP));
                prev = current;
            }

        }
    }

    private void processBlock2(List<Sensor> block, int blockNumber, Sensor prev, int start, double k) {
        int o = 1;

        for (int c = start; c < block.size(); c++ ) {
            Sensor current = block.get(c);
            if (current.getX() < 1820) {
                break;
            }
            if (isWithLength(blockNumber, current)) {
                o++;
                current.setStartLengthValue(prev.getEndLengthValue().setScale(2, RoundingMode.HALF_UP));
                current.setEndLengthValue(current.getStartLengthValue().add(getLength(blockNumber, o)).setScale(2, RoundingMode.HALF_UP));
                current.setStartFileLengthValue(prev.getEndFileLengthValue().setScale(4, RoundingMode.HALF_UP));
                current.setEndFileLengthValue(current.getEndLengthValue().subtract(current.getStartLengthValue())
                        .multiply(new BigDecimal(k)).add(current.getStartFileLengthValue()).setScale(4, RoundingMode.HALF_UP));
                prev = current;
            }
        }

        prev = new Sensor();
        prev.setStartLengthValue(new BigDecimal(297));
        prev.setEndLengthValue(new BigDecimal(330));
        prev.setStartFileLengthValue(new BigDecimal(1016.9));
        prev.setEndFileLengthValue(new BigDecimal(983.4));

        o = 71;

        for (int c = start; c < block.size(); c++ ) {
            Sensor current = block.get(c);
            if (current.getX() > 1670) {
                continue;
            }
            if (isWithLength(blockNumber, current)) {
                o++;
                current.setStartLengthValue(prev.getEndLengthValue().setScale(2, RoundingMode.HALF_UP));
                current.setEndLengthValue(current.getStartLengthValue().add(getLength(blockNumber, o)).setScale(2, RoundingMode.HALF_UP));
                current.setStartFileLengthValue(prev.getEndFileLengthValue().setScale(4, RoundingMode.HALF_UP));
                current.setEndFileLengthValue(current.getEndLengthValue().subtract(current.getStartLengthValue())
                        .multiply(new BigDecimal(k)).add(current.getStartFileLengthValue()).setScale(4, RoundingMode.HALF_UP));
                prev = current;
            }
        }

        prev = block.get(block.size() - 2);
        prev.setStartFileLengthValue(new BigDecimal(12));
        prev.setEndFileLengthValue(new BigDecimal(15.6).setScale(1, RoundingMode.HALF_UP));

        for (int c = block.size() - 3; c >= start; c--) {
            Sensor current = block.get(c);
            if (current.getX() > 1670) {
                break;
            }
            if (isWithLength(blockNumber, current)) {
                current.setStartFileLengthValue(prev.getEndFileLengthValue().setScale(4, RoundingMode.HALF_UP));
                current.setEndFileLengthValue(current.getEndLengthValue().subtract(current.getStartLengthValue())
                        .multiply(new BigDecimal(k)).add(current.getStartFileLengthValue()).setScale(4, RoundingMode.HALF_UP));
                prev = current;
            }
        }

        prev = block.get(17);
        prev.setStartFileLengthValue(new BigDecimal(1195.5).setScale(1, RoundingMode.HALF_UP));
        prev.setEndFileLengthValue(new BigDecimal(1197.5).setScale(1, RoundingMode.HALF_UP));

        for (int c = 16; c >= start; c--) {
            Sensor current = block.get(c);
            if (current.getX() < 1820) {
                continue;
            }
            if (isWithLength(blockNumber, current)) {
                current.setStartFileLengthValue(prev.getEndFileLengthValue().setScale(4, RoundingMode.HALF_UP));
                current.setEndFileLengthValue(current.getEndLengthValue().subtract(current.getStartLengthValue())
                        .multiply(new BigDecimal(k)).add(current.getStartFileLengthValue()).setScale(4, RoundingMode.HALF_UP));
                prev = current;
            }
        }
    }

    private void processBlock4(List<Sensor> block, Sensor prev) {
        int o = 1;

        for (int c = 1; c < block.size(); c++ ) {
            Sensor current = block.get(c);
            if (isWithLength(4, current)) {
                o++;
                current.setStartLengthValue(prev.getEndLengthValue().setScale(2, RoundingMode.HALF_UP));
                current.setEndLengthValue(current.getStartLengthValue().subtract(getLength(4, o)).setScale(2, RoundingMode.HALF_UP));
                current.setStartFileLengthValue(prev.getEndFileLengthValue().setScale(4, RoundingMode.HALF_UP));
                current.setEndFileLengthValue(current.getStartLengthValue().subtract(current.getEndLengthValue())
                        .multiply(new BigDecimal(1.015)).add(current.getStartFileLengthValue()).setScale(4, RoundingMode.HALF_UP));
                prev = current;
            }

        }
    }

    private boolean isWithLength(int block, Sensor sensor) {
        if (block == 1) {
            if (sensor.getY() < 230 && (sensor.getX() > 480 || sensor.getX() < 390) && !(sensor.getX() > 370 && sensor.getX() < 380)) {
                return false;
            }
            if (sensor.getX() > 340 && sensor.getX() < 390 && sensor.getY() > 270) {
                return false;
            }
            if (sensor.getX() > 315 && sensor.getX() < 340 && sensor.getY() > 290) {
                return false;
            }
            if (sensor.getX() < 230 && sensor.getY() > 310) {
                return false;
            }
        } else if (block == 4) {
            if (sensor.getY() > 700) {
                return false;
            }
            if (sensor.getX() > 1630 && sensor.getY() > 600) {
                return false;
            }
            if (sensor.getX() > 1680 && sensor.getY() > 530) {
                return false;
            }
        } else  if (block == 3) {
            if (sensor.getY() > 700 && sensor.getX() > 880 && sensor.getX() < 910) {
                return true;
            }
            if (sensor.getY() < 700 && sensor.getX() > 860 && sensor.getX() < 910) {
                return false;
            }
            if (sensor.getY() > 700 && sensor.getX() < 970) {
                return false;
            }
            if (sensor.getY() < 550 && sensor.getX() > 470 && sensor.getX() < 520) {
                return false;
            }
            if (sensor.getY() < 530 && sensor.getX() > 400 && sensor.getX() < 440) {
                return false;
            }
            if (sensor.getY() < 490 && sensor.getX() > 280 && sensor.getX() < 330) {
                return false;
            }
            if (sensor.getY() > 690 && sensor.getX() > 270 && sensor.getX() < 280) {
                return false;
            }
            if (sensor.getY() > 480 && sensor.getX() > 250 && sensor.getX() < 260) {
                return false;
            }
        } else if (block == 2) {
            if (sensor.getY() < 210 && sensor.getX() > 1815 && sensor.getX() < 1845) {
                return false;
            }
            if (sensor.getY() > 260 && sensor.getX() > 1830) {
                return false;
            }
            if (sensor.getY() < 210 && sensor.getX() > 1830 && sensor.getX() < 1820) {
                return false;
            }
            if (sensor.getY() < 230 && sensor.getX() > 1120 && sensor.getX() < 1660) {
                return false;
            }
        }
        return true;
    }

    private BigDecimal getLength(int block, int id) {
        if (block == 1) {
            if (Arrays.asList(79).contains(id)) {
                return new BigDecimal(0.5);
            } else if (Arrays.asList(134, 253, 389, 390, 406, 409).contains(id)) {
                return new BigDecimal(1.5);
            } else if (Arrays.asList(37, 410).contains(id)) {
                return new BigDecimal(2);
            } else if (Arrays.asList(109, 157, 163,181, 182, 205, 206, 230, 300, 301, 326, 337, 338,
                    361, 373, 374, 385).contains(id)) {
                return new BigDecimal(3.5);
            } else if (Arrays.asList(394).contains(id)) {
                return new BigDecimal(4);
            } else if (Arrays.asList(49, 50, 122, 133, 146, 169, 171, 193, 194, 218, 229, 241, 242, 265,
                    266, 277, 289, 290, 313, 314, 350, 362, 418).contains(id)) {
                return new BigDecimal(4.5);
            } else if (Arrays.asList(388, 402).contains(id)) {
                return new BigDecimal(5);
            } else if (Arrays.asList(97, 98, 121, 145, 217, 254, 325, 349, 414).contains(id)) {
                return new BigDecimal(5.5);
            } else if (Arrays.asList(399, 433).contains(id)) {
                return new BigDecimal(6);
            } else if (Arrays.asList(396, 426, 430).contains(id)) {
                return new BigDecimal(6.5);
            } else if (Arrays.asList(24, 391, 405).contains(id)) {
                return new BigDecimal(7);
            } else if (Arrays.asList(422).contains(id)) {
                return new BigDecimal(7.5);
            } else if (Arrays.asList(73).contains(id)) {
                return new BigDecimal(8.5);
            }
        } else if (block == 3) {
            if (Arrays.asList(230).contains(id)) {
                return new BigDecimal(-1.5);
            } else if (Arrays.asList().contains(id)) {
                return new BigDecimal(0.5);
            } else if (Arrays.asList(294).contains(id)) {
                return new BigDecimal(1);
            } else if (Arrays.asList(328, 329, 339, 341, 344).contains(id)) {
                return new BigDecimal(1.5);
            } else if (Arrays.asList(50, 291).contains(id)) {
                return new BigDecimal(2);
            } else if (Arrays.asList(309, 387).contains(id)) {
                return new BigDecimal(3);
            } else if (Arrays.asList(7, 43, 49, 62, 80, 86, 110, 129, 152, 158, 170, 182, 188, 200, 206,
                    212, 224, 226, 227, 228, 229, 231, 248, 254, 272, 319, 349, 350, 360, 361, 382, 383, 396, 397, 398,
                    399, 400, 401).contains(id)) {
                return new BigDecimal(3.5);
            } else if (Arrays.asList(310).contains(id)) {
                return new BigDecimal(4);
            } else if (Arrays.asList(14, 25, 26, 56, 74, 75, 98, 99, 104, 123, 146, 147, 169, 176, 194,
                    195, 218, 219, 243, 266, 267, 282, 283, 301, 318, 338, 371, 372).contains(id)) {
                return new BigDecimal(4.5);
            } else if (Arrays.asList(394).contains(id)) {
                return new BigDecimal(5);
            } else if (Arrays.asList(122, 242, 276, 300, 402).contains(id)) {
                return new BigDecimal(5.5);
            } else if (Arrays.asList().contains(id)) {
                return new BigDecimal(6);
            } else if (Arrays.asList().contains(id)) {
                return new BigDecimal(6.5);
            } else if (Arrays.asList().contains(id)) {
                return new BigDecimal(7);
            } else if (Arrays.asList().contains(id)) {
                return new BigDecimal(7.5);
            } else if (Arrays.asList().contains(id)) {
                return new BigDecimal(8);
            } else if (Arrays.asList(273, 274).contains(id)) {
                return new BigDecimal(8.5);
            } else if (Arrays.asList(275).contains(id)) {
                return new BigDecimal(9.5);
            }
        } else if (block == 4) {
            if (Arrays.asList().contains(id)) {
                return new BigDecimal(0.5);
            } else if (Arrays.asList(2, 14, 205, 391, 392, 393, 396, 397, 398, 408, 409).contains(id)) {
                return new BigDecimal(1.5);
            } else if (Arrays.asList().contains(id)) {
                return new BigDecimal(2);
            } else if (Arrays.asList(389).contains(id)) {
                return new BigDecimal(3);
            } else if (Arrays.asList(12, 13, 26, 27, 31, 55, 61, 67, 79, 127, 133, 134, 157, 163, 175, 211, 229,
                    235, 247, 253, 254, 271, 277, 283, 301, 307, 331, 343, 355, 367, 379, 412, 413, 418).contains(id)) {
                return new BigDecimal(3.5);
            } else if (Arrays.asList(384, 419).contains(id)) {
                return new BigDecimal(4);
            } else if (Arrays.asList(25, 49, 73, 74, 97, 103, 121, 122, 169, 170, 181, 199, 206, 217, 223, 242,
                    265, 266, 289, 295, 319, 338, 362, 402, 403).contains(id)) {
                return new BigDecimal(4.5);
            } else if (Arrays.asList().contains(id)) {
                return new BigDecimal(5);
            } else if (Arrays.asList(145, 146, 193, 241, 313, 337, 361).contains(id)) {
                return new BigDecimal(5.5);
            } else if (Arrays.asList().contains(id)) {
                return new BigDecimal(6);
            } else if (Arrays.asList().contains(id)) {
                return new BigDecimal(6.5);
            } else if (Arrays.asList().contains(id)) {
                return new BigDecimal(7);
            } else if (Arrays.asList().contains(id)) {
                return new BigDecimal(7.5);
            } else if (Arrays.asList(404).contains(id)) {
                return new BigDecimal(8);
            } else if (Arrays.asList(368, 369, 370, 371, 372, 374, 375, 376, 377, 378).contains(id)) {
                return new BigDecimal(8.5);
            }
        } else if (block == 2) {
            if (Arrays.asList(26, 27).contains(id)) {
                return new BigDecimal(-5);
            } else if (Arrays.asList(396, 397, 419, 420, 421).contains(id)) {
                return new BigDecimal(0.5);
            } else if (Arrays.asList(57, 58, 84, 127, 157, 175, 252, 253, 276, 422, 424).contains(id)) {
                return new BigDecimal(1.5);
            } else if (Arrays.asList(14, 30, 31, 32, 33, 34, 40, 50, 55, 59, 78).contains(id)) {
                return new BigDecimal(2);
            } else if (Arrays.asList(4, 5, 6, 7, 10, 11, 12, 13, 19, 20, 36, 41, 44, 45, 46, 47, 48, 49, 60, 61,
                    62, 63, 64, 65, 66, 70, 71).contains(id)) {
                return new BigDecimal(3);
            } else if (Arrays.asList(85, 97, 98, 100, 122, 146, 156, 205, 290, 292, 300, 301, 313, 324, 325, 336,
                    337, 349, 360, 361, 372, 373, 385, 432).contains(id)) {
                return new BigDecimal(3.5);
            } else if (Arrays.asList(15, 16, 17, 18, 35, 37, 38, 39, 67, 68).contains(id)) {
                return new BigDecimal(4);
            } else if (Arrays.asList(120, 121, 144, 145, 193, 241, 265, 288, 289, 312, 348, 384).contains(id)) {
                return new BigDecimal(4.5);
            } else if (Arrays.asList(69).contains(id)) {
                return new BigDecimal(5);
            } else if (Arrays.asList(168, 169, 192, 240, 264, 409).contains(id)) {
                return new BigDecimal(5.5);
            } else if (Arrays.asList(9).contains(id)) {
                return new BigDecimal(6);
            } else if (Arrays.asList(216, 408).contains(id)) {
                return new BigDecimal(6.5);
            } else if (Arrays.asList(28).contains(id)) {
                return new BigDecimal(7);
            } else if (Arrays.asList().contains(id)) {
                return new BigDecimal(7.5);
            } else if (Arrays.asList().contains(id)) {
                return new BigDecimal(8);
            } else if (Arrays.asList().contains(id)) {
                return new BigDecimal(8.5);
            } else if (Arrays.asList(2, 3, 29).contains(id)) {
                return new BigDecimal(12);
            } else if (Arrays.asList(8).contains(id)) {
                return new BigDecimal(13);
            } else if (Arrays.asList(25).contains(id)) {
                return new BigDecimal(20);
            }
        }
        return new BigDecimal(2.5);
    }

    public Sensor createSensor(int id, double x, double y) {
        Sensor sensor = new Sensor();
        sensor.setId(id);
        sensor.setX(x);
        sensor.setY(y);

        sensor.setLengthValue("99999");

        return sensor;
    }

    public void buildData(Model model) {
        List<Sensor> sensors = new ArrayList<>();
        BigDecimal start = new BigDecimal("16.80");

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        int y = 664;
        int x = 117;

        int k = -1;

        List<Criterion> criterions = new ArrayList<>();

        int b = 255;

        int r = 0;

        for (int i = 0; i < 50; i++) {
            Criterion criterion = new Criterion();
            criterion.setId(i + 1);
            criterion.setStartValue(i != 0 ? i : -999999999);
            criterion.setEndValue(i != 49 ? i+1 : 999999999);
            criterion.setBlue(b);
            criterion.setRed(r);
            b -= 5;
            r += 5;
            criterions.add(criterion);
        }

        Map<Long, SensorValue> sensorValues = new HashMap<>();

        for (int i = 1; i <= 61; i++) {
            Sensor sensor = new Sensor();
            sensor.setId(i);
            sensor.setX(x);
            sensor.setY(y);

            if (i != 1 && (i - 1) % 6 == 0 && i != 61) {
                sensor.setWidth(sensor.getWidth() * 2);
            }

            if (i != 1 && i != 2 && (i - 1) % 6 == 0) {
                x += SQUERE;
            }

            if (i != 1 && (i - 1) % 6 == 0) {
                k = -1 * k;
            }

            y += k * SQUERE;

            sensor.setLengthValue("" + start.toString());
            sensors.add(sensor);

            start = start.add(new BigDecimal("0.2"));

            SensorValue value = new SensorValue();
            value.setId(sensor.getId());
            value.setValue(i);
            value.setDate(date);
            value.setTime(time);
            for (Criterion criterion : criterions) {
                if (value.getValue() >= criterion.getStartValue() && value.getValue() < criterion.getEndValue()) {
                    value.setColor(criterion.getColor());
                    break;
                }
            }


            sensorValues.put(value.getId(), value);
        }

        model.addAttribute("sensors", sensors);
        model.addAttribute("sensorValues", sensorValues);
        model.addAttribute("minValue", sensorValues.values().stream().min((v1,v2) -> (int) (v1.getValue() - v2.getValue())).get().getValue() - 1);
        model.addAttribute("maxValue", sensorValues.values().stream().max((v1,v2) -> (int) (v1.getValue() - v2.getValue())).get().getValue() + 1);

//        criterions.forEach(c -> System.out.println("insert into criterion values(" + c.getId() +
//                "," + c.getStartValue() + "," + c.getEndValue() + "," + c.getRed() + "," + c.getBlue()
//                + "," + c.getGreen()  + "," + c.getIntensity() + ");"));

//        sensors.forEach(s -> System.out.println("insert into sensor values(" +
//                s.getId() + "," + s.getLengthValue() + "," + s.getX() + "," + s.getY()
//                + "," + s.getWidth() + "," + s.getHeight() + ");"));

//        sensorValues.values().forEach(sv -> System.out.println("insert into sensor_value values (" +
//                sv.getId() + "," + sv.getId() + "," + sv.getValue() + ",CURRENT_DATE,CURRENT_TIME" +");"));
//
//        sensorValues.values().forEach(sv -> System.out.println("insert into sensor_value_storage values (" +
//                sv.getId() + "," + sv.getId() + "," + sv.getValue() + ",CURRENT_DATE,CURRENT_TIME" +");"));
    }

}
