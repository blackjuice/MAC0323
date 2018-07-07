/*************************************************************************
 *  Compilation:  javac Date.java
 *  Execution:    java Date
 *
 *  An immutable data type for dates.
 *
 *************************************************************************/

// The <tt>Date</tt> class is an immutable data type to encapsulate a
// date (day, month, and year).
// <p>
// For additional documentation, 
// see <a href="http://algs4.cs.princeton.edu/12oop/">Section 1.2</a> of
// <i>Algorithms, 4th Edition</i>, by Robert Sedgewick and Kevin Wayne.
//
// @author Robert Sedgewick
// @author Kevin Wayne
/** Esta classe implementa o ADT Date, um tipo de dados imutável 
 * que encapsula uma data (dia, mês, e ano).
 * <p>
 * Documentaação adicional: 
 * veja <a href="http://algs4.cs.princeton.edu/12oop/">Seção 1.2</a>
 * do livro Algorithms, 4th Edition, de Robert Sedgewick e Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */

public class Date implements Comparable<Date> {

    private static final int[] DAYS 
          = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    private final int month;   // month (between 1 and 12)
    private final int day;     // day   (between 1 and DAYS[month]
    private final int year;    // year

    // Initializes a new date from the month, day, and year.
    // @param month the month (between 1 and 12)
    // @param day the day (between 1 and 28-31, depending on the month)
    // @param year the year
    // @throws IllegalArgumentException if the date is invalid
   /** Cria uma nova data a partir dos argumento
     * month (mês), day (dia) e year (ano).
     * O argumento month deve estar em 1..12.
     * O argumento day deve estar em 1..31, em 1..30, em 1..29 e 1.28
     * dependendo do valor de month.
     * O argumento year deve ser positivo.
     * @throws IllegalArgumentException se a data é inválida.
     */
    public Date(int month, int day, int year) {
        if (!isValid(month, day, year)) throw new IllegalArgumentException("Invalid date");
        this.month = month;
        this.day   = day;
        this.year  = year;
    }
 
    // Initializes new date specified as a string in form MM/DD/YYYY.
    // @param date the string representation of the date
    // @throws IllegalArgumentException if the date is invalid
    /** Inicializa uma nova data a partir da string date,
     * que tem a forma MM/DD/YYYY.
     * @throws IllegalArgumentException se a data for inválida.
     */
    public Date(String date) {
        String[] fields = date.split("/");
        if (fields.length != 3) {
            throw new IllegalArgumentException("Invalid date");
        }
        month = Integer.parseInt(fields[0]);
        day   = Integer.parseInt(fields[1]);
        year  = Integer.parseInt(fields[2]);
        if (!isValid(month, day, year)) throw new IllegalArgumentException("Invalid date");
    }

    // Return the month.
    // @return the month (an integer between 1 and 12)
    /** Devolve o mês (um inteiro em 1..12) desta data.
     */
    public int month() {
        return month;
    }

    // Return the day.
    // @return the day (an integer between 1 and 31)
    /** Devolve o dia (um inteiro em 1..31) desta data.
     */
    public int day() {
        return day;
    }

    // Return the year.
    // @return the year
    /** Devolve o ano desta data.
     */
    public int year() {
        return year;
    }


    // is the given date valid?
    private static boolean isValid(int m, int d, int y) {
        if (m < 1 || m > 12)      return false;
        if (d < 1 || d > DAYS[m]) return false;
        if (m == 2 && d == 29 && !isLeapYear(y)) return false;
        return true;
    }

    // Is year y a leap year?
    // @return true if y is a leap year; false otherwise
    /** O nao y é bissexto?
     */
    private static boolean isLeapYear(int y) {
        if (y % 400 == 0) return true;
        if (y % 100 == 0) return false;
        return y % 4 == 0;
    }

    // Returns the next date in the calendar.
    // @return a date that represents the next day after this day
    /** Devolve a data seguinte do calendário.
     */
    public Date next() {
        if (isValid(month, day + 1, year))    return new Date(month, day + 1, year);
        else if (isValid(month + 1, 1, year)) return new Date(month + 1, 1, year);
        else                                  return new Date(1, 1, year + 1);
    }

    // Is this date after b?
    // @return true if this date is after date b; false otherwise
    /** Esta data vem depois da data b?
     */
    public boolean isAfter(Date b) {
        return compareTo(b) > 0;
    }

    // Is this date before b?
    // @return true if this date is before date b; false otherwise
    /** Esta data vem antes da data b?
     */
    public boolean isBefore(Date b) {
        return compareTo(b) < 0;
    }

    // Compare this date to that date.
    // @return { a negative integer, zero, or a positive integer }, depending
    //   on whether this date is { before, equal to, after } that date
    /** Compara esta data com that.
     * Devolve um inteiro negativo, zero, ou um inteiro positivo
     * dependendo de esta data ser anterior, igual, ou posterior a that.
     */
    public int compareTo(Date that) {
        if (this.year  < that.year)  return -1;
        if (this.year  > that.year)  return +1;
        if (this.month < that.month) return -1;
        if (this.month > that.month) return +1;
        if (this.day   < that.day)   return -1;
        if (this.day   > that.day)   return +1;
        return 0;
    }

    // Return a string representation of this date.
    // @return the string representation in the foramt MM/DD/YYYY
    /** Devolve uma string que representa esta data.
     * Formato: MM/DD/YYYY.
     */
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    // Is this date equal to x?
    // @return true if this date equals x; false otherwise
    /** Esta data é igual a xx?
     */
    public boolean equals(Object x) {
        if (x == this) return true;
        if (x == null) return false;
        if (x.getClass() != this.getClass()) return false;
        Date that = (Date) x;
        return (this.month == that.month) && (this.day == that.day) && (this.year == that.year);
    }

    // Return a hash code.
    // @return a hash code for this date
    /** Devolve um código hash desta data.
     */
    public int hashCode() {
        int hash = 17;
        hash = 31*hash + month;
        hash = 31*hash + day;
        hash = 31*hash + year;
        return hash;
    }

    // Unit tests the date data type.
    /** Teste de unidade desta classe.
     *  Toma 2014-02-25 como data de referência.
     *  Calcula a próxima data, a próxima da próxima, etc.
     *  repetindo 10 vezes.
     * ???????????????????????????????
     * <p>
     * Define birthday como 16/10/1971.
     * Calcula ????????????????????
     */
    public static void main(String[] args) {
        Date today = new Date(2, 25, 2004);
        StdOut.println(today);
        for (int i = 0; i < 10; i++) {
            today = today.next();
            StdOut.println(today);
        }

        StdOut.println(today.isAfter(today.next()));
        StdOut.println(today.isAfter(today));
        StdOut.println(today.next().isAfter(today));


        Date birthday = new Date(10, 16, 1971);
        StdOut.println(birthday);
        for (int i = 0; i < 10; i++) {
            birthday = birthday.next();
            StdOut.println(birthday);
        }
    }

}

