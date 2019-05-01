import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Record implements Comparable<Record>{
    String name;
    HashSet<String> phonesSet = new HashSet<>();

    //---------------------------------------------------------------------------------
    Record(String name,String phone){
        this.name=name;
        this.phonesSet.add(phone);
    }

    //---------------------------------------------------------------------------------
    void showInfo(){
        System.out.println(name);
        for (String phone : phonesSet) {
            System.out.print(phone + " ");
        }
        System.out.println();
    }

    //---------------------------------------------------------------------------------
    @Override
    public int compareTo(Record record) { //переопределим сравнение , чтобы сравнивало лексографически по именам
        if(this.name.compareTo(record.name) > 0){
            return 1;
        }else if (this.name.compareTo(record.name) < 0){
            return -1;
        }else return 0;
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////
class Records{
    SortedSet<Record> records= new TreeSet<Record>();

    //---------------------------------------------------------------------------------
    String formatPhone(String phone){ //форматируем телефон и возвращаем форматированный
        String formattedPhone;
        formattedPhone= phone.replaceAll("[\\-\\+\\(\\)]",""); // тут \\*   -> * это заменяемый символ, можно включать несколько(без запятых)
        if( formattedPhone.length() != 7 && formattedPhone.charAt(0) == '8' ){
            formattedPhone = formattedPhone.replaceFirst("8","7");
        }
        if( formattedPhone.length() == 7 ){
            formattedPhone= "+7495" + formattedPhone;
        }else {
            formattedPhone="+" + formattedPhone;
        }
        return formattedPhone;
    }

    //---------------------------------------------------------------------------------
    boolean addRecord(String name,String phone) { //Будет добавлять , основываясь на сравнении лексографически
        String formattedPhone = formatPhone(phone);
        if(formattedPhone.length() != 12){
            return false; //После форматированния корректный телефон будет иметь СТРОГО длину 12
            //если не корректен, не будем добавлять запись
        }
        Record tmp = findByName(name); //Ищем запись с таким именем
        if(tmp!= null){ //если нашли, то
            tmp.phonesSet.add(formattedPhone);
        }
        else{ //если записи с таким именем нету, то делаем новую
            return records.add(new Record(name, formattedPhone));
        }
        return false; //Обновили телефон , но не добавли новую запись
    }

    //--------------------------------------------------------------------------------
    void showAllRecords(){
        for(Record cur: records){
            cur.showInfo();
        }
    }

    //---------------------------------------------------------------------------------
    Record findByName(String name){
        Iterator<Record> iter = records.iterator();
        Record cur;
        while (iter.hasNext()) {
            cur = iter.next();
            if(cur.name.equals(name)){
             return cur;
            }
        }
        return null; //не нашли
    }

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////
public class runMe {
    public static void main(String ...args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Records records=new Records();
        String currLine;
        String currName=null;
        String currTelephone=null;
        //Согласно условию, строки идут строго парно, 1ая имя, 2ая номер
        boolean nextLineIsName=true;
        boolean newRecordReady=false;
        while ((currLine = reader.readLine()) != null ) { //если вставлен столбец строк, то нужно 2 нажатия для завершения чтения: 1ое для считывание последней строки, 2ое для считывания пустой строки после последней
            if(currLine.trim().isEmpty()) { //trim() возвращает кописю строки, без пробелов, \n и т.д., т.е. только текст , если текста нету, прерываем чтение
                break;
            }
            System.out.println("curr readline = "+currLine);
            if(nextLineIsName){
                currName = currLine;
                nextLineIsName = false;
            }else{
                currTelephone = currLine;
                nextLineIsName = true;
                newRecordReady = true; //Оба данных есть
            }
            if(newRecordReady) {
                records.addRecord(currName,currTelephone); //Не добавит, если после форматирования телефон не корректен
                newRecordReady = false;
            }

        }
        System.out.println("reader closed");
        reader.close();
        records.showAllRecords();
    }
}
