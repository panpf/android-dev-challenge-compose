package com.example.androiddevchallenge.utils;

import org.jetbrains.annotations.NotNull;

public class DateUtils {
    /**
     * Format the length of time according to the specified 'pattern'
     *
     * @param pattern Formatting pattern，The following types are supported:
     *                <blockquote>
     *                <table summary="Time length formatting pattern desc">
     *                  <tr>
     *                      <td>%d、%d?、%D、%D?</td>
     *                      <td>Day，</td>
     *                  </tr>
     *                  <tr>
     *                      <td>%h、%h?、%H、%H?</td>
     *                      <td>Hour</td>
     *                  </tr>
     *                  <tr>
     *                      <td>%m、%m?、%M、%M?</td>
     *                      <td>Minute</td>
     *                  </tr>
     *                  <tr>
     *                      <td>%s、%s?、%S、%S?</td>
     *                      <td>Second</td>
     *                  </tr>
     *                  <tr>
     *                      <td>%ms、%ms?、%MS、%MS?</td>
     *                      <td>Millisecond</td>
     *                  </tr>
     *                </table>
     *                </blockquote>
     *                As shown in the table above, each type has several variants. The variant containing'?' means that if the result of this item is 0, then it can be ignored in the output；Uppercase means, if the result of this item is less than 10, then add '0' in front of the output
     *
     *                for example:
     *                <blockquote>
     *                <table summary="Time length formatting pattern example">
     *                  <tr>
     *                      <th> Example </th>
     *                      <th> Result </th>
     *                  </tr>
     *                  <tr>
     *                      <td> formatTimeLength(3623000, "%hh%mm%ss") </td>
     *                      <td> "1h0m23s" </td>
     *                  </tr>
     *                  <tr>
     *                      <td> formatTimeLength(3623000, "%h?h%m?m%s?s") </td>
     *                      <td> "1h23s" </td>
     *                  </tr>
     *                  <tr>
     *                      <td> formatTimeLength(4684000, "%h:%m:%s") </td>
     *                      <td> "1:18:4" </td>
     *                  </tr>
     *                  <tr>
     *                      <td> formatTimeLength(4684000, "%H:%M:%S") </td>
     *                      <td> "01:18:04" </td>
     *                  </tr>
     *                  <tr>
     *                      <td> formatTimeLength(91403467, "%dDay %hHour %mMinute %sSecond %msMillisecond") </td>
     *                      <td> "1Day 1Hour 23Minute 23Second 467Millisecond" </td>
     *                  </tr>
     *                </table>
     *                </blockquote>
     */
    @NotNull
    public static String formatTimeLength(long timeLength, @NotNull String pattern) {
        timeLength = Math.max(timeLength, 0);
        final long oneSecondMilliseconds = 1000;
        final long oneMinuteMilliseconds = oneSecondMilliseconds * 60;
        final long oneHourMilliseconds = oneMinuteMilliseconds * 60;
        final long oneDayMilliseconds = oneHourMilliseconds * 24;

        String[] items = pattern.split("%");
        String daySuffix = null, hourSuffix = null, minuteSuffix = null, secondSuffix = null, millisecondSuffix = null;
        boolean dayAllowOmit = false, hourAllowOmit = false, minuteAllowOmit = false, secondAllowOmit = false, millisecondAllowOmit = false;
        boolean dayPad = false, hourPad = false, minutePad = false, secondPad = false, millisecondPad = false;
        for (String item : items) {
            if (item.toLowerCase().startsWith("ms")) {
                millisecondAllowOmit = item.length() > 2 && item.charAt(2) == '?';
                millisecondSuffix = item.substring(millisecondAllowOmit ? 3 : 2);
                millisecondPad = item.startsWith("MS");
            } else if (item.toLowerCase().startsWith("s")) {
                secondAllowOmit = item.length() > 1 && item.charAt(1) == '?';
                secondSuffix = item.substring(secondAllowOmit ? 2 : 1);
                secondPad = item.startsWith("S");
            } else if (item.toLowerCase().startsWith("m")) {
                minuteAllowOmit = item.length() > 1 && item.charAt(1) == '?';
                minuteSuffix = item.substring(minuteAllowOmit ? 2 : 1);
                minutePad = item.startsWith("M");
            } else if (item.toLowerCase().startsWith("h")) {
                hourAllowOmit = item.length() > 1 && item.charAt(1) == '?';
                hourSuffix = item.substring(hourAllowOmit ? 2 : 1);
                hourPad = item.startsWith("H");
            } else if (item.toLowerCase().startsWith("d")) {
                dayAllowOmit = item.length() > 1 && item.charAt(1) == '?';
                daySuffix = item.substring(dayAllowOmit ? 2 : 1);
                dayPad = item.startsWith("D");
            }
        }
        if (daySuffix == null && hourSuffix == null && minuteSuffix == null && secondSuffix == null && millisecondSuffix == null) {
            throw new IllegalArgumentException("Invalid pattern '" + pattern + "', for example：'\\h:\\m:\\s'");
        }

        StringBuilder builder = new StringBuilder();
        if (daySuffix != null) {
            long day = timeLength / oneDayMilliseconds;
            if (day > 0) {
                builder.append(dayPad ? String.format("%02d", day) : day).append(daySuffix);
            } else if (!dayAllowOmit) {
                builder.append(dayPad ? String.format("%02d", 0) : 0).append(daySuffix);
            }
        }

        if (hourSuffix != null) {
            long hour;
            if (daySuffix != null) {
                hour = timeLength % oneDayMilliseconds / oneHourMilliseconds;
            } else {
                hour = timeLength / oneHourMilliseconds;
            }
            if (hour > 0) {
                builder.append(hourPad ? String.format("%02d", hour) : hour).append(hourSuffix);
            } else if (!hourAllowOmit) {
                builder.append(hourPad ? String.format("%02d", 0) : 0).append(hourSuffix);
            }
        }

        if (minuteSuffix != null) {
            long minute;
            if (daySuffix != null || hourSuffix != null) {
                minute = timeLength % oneHourMilliseconds / oneMinuteMilliseconds;
            } else {
                minute = timeLength / oneMinuteMilliseconds;
            }
            if (minute > 0) {
                builder.append(minutePad ? String.format("%02d", minute) : minute).append(minuteSuffix);
            } else if (!minuteAllowOmit) {
                builder.append(minutePad ? String.format("%02d", 0) : 0).append(minuteSuffix);
            }
        }

        if (secondSuffix != null) {
            long second;
            if (daySuffix != null || hourSuffix != null || minuteSuffix != null) {
                second = timeLength % oneMinuteMilliseconds / oneSecondMilliseconds;
            } else {
                second = timeLength / oneSecondMilliseconds;
            }
            if (second > 0) {
                builder.append(secondPad ? String.format("%02d", second) : second).append(secondSuffix);
            } else if (!secondAllowOmit) {
                builder.append(secondPad ? String.format("%02d", 0) : 0).append(secondSuffix);
            }
        }

        if (millisecondSuffix != null) {
            long millisecond;
            if (daySuffix != null || hourSuffix != null || minuteSuffix != null || secondSuffix != null) {
                millisecond = timeLength % oneSecondMilliseconds;
            } else {
                millisecond = timeLength;
            }
            if (millisecond > 0) {
                builder.append(millisecondPad ? String.format("%03d", millisecond) : millisecond).append(millisecondSuffix);
            } else if (!millisecondAllowOmit) {
                builder.append(millisecondPad ? String.format("%03d", 0) : 0).append(millisecondSuffix);
            }
        }

        if (builder.length() == 0) {
            if (millisecondSuffix != null) {
                builder.append(millisecondPad ? String.format("%03d", 0) : 0).append(millisecondSuffix);
            } else if (secondSuffix != null) {
                builder.append(secondPad ? String.format("%02d", 0) : 0).append(secondSuffix);
            } else if (minuteSuffix != null) {
                builder.append(minutePad ? String.format("%02d", 0) : 0).append(minuteSuffix);
            } else if (hourSuffix != null) {
                builder.append(hourPad ? String.format("%02d", 0) : 0).append(hourSuffix);
            } else {
                builder.append(dayPad ? String.format("%02d", 0) : 0).append(daySuffix);
            }
        }

        return builder.toString().trim();
    }
}
