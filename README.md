# telephoneBook
Телефонная книга 

ввод : stdin 

вывод: stdout

Вам дана телефонная книга в виде последовательности пар строк. Первая строка — имя абонента, вторая — его номер.

Телефон может быть в одном из форматов:

+7xxxxxxxxxx (ровно 11 цифр, «x» — любая цифра)

8xxxxxxxxxx (ровно 11 цифр,  «x» — любая цифра)

xxxxxxx (ровно 7 цифр, «x» — любая цифра)

Кроме цифр в произвольные позиции телефонных номеров могут быть вставлены дефисы и скобки.
Например, телефон «+7(917)100-00-00 » считается корректным. Требуется упорядочить телефонную книгу, используя следующие правила:
объедините контакты с одинаковыми именами (регистр букв имеет значение);
приведите все телефоны к одной записи вида « +7xxxxxxxxxx » (ровно 11 цифр, без дополнительных символов), при этом если телефон соответствует третьему формату, то добавьте в начало префикс «+7495»;
удалите дубликаты в списке телефонов каждого абонента;
при обработке пропустите записи из книги, в которых телефон не подходит ни под один из указанных форматов.

Входные данные

Входные данные состоят из набора пар строк, где первая строка означает ИМЯ, а вторая — номер телефона. ИМЯ — это непустая
последовательность заглавных и строчных латинских букв и пробелов. ИМЯ не может начинаться ИЛИ заканчиваться пробелом. 

Телефон — это непустая последовательность из цифр, скобок и знаков «+» и «-». Входные данные состоят не более чем из 1000 строк.

Выходные данные

Выведите телефонную книгу в виде последовательность пар строк, в первой строке выведите имя абонента, во второй — список телефонов в
лексикографическом порядке, относящихся к этому абоненту. Для каждого из абонентов его список телефонов не должен содержать дубликатов.
Описания абонентов выводите в лексикографическом порядке их имен. Если для абонента в записной книжке не указано ни одного корректного номера, то ВЫВОДИТЬ его не следует.

ПРИМЕРЫ

входные данные 

 Vincent van Gogh 
 
 +79170123456 
 
 Archimedes 
 
 8(911)321-85-85 
 
 Vincent van Gogh 
 
 321-85-85 
 
 Vincent van Gogh 
 
 18238585 
 
 George Washington 
 
 +7-(919)-01-23-4-5-6
 
 Peter 
 
 8 
 
 
выходные данные 
 Archimedes
 
 +79113218585 
 
 George Washington 
 
 +79190123456 
 
 Vincent van Gogh 
 
 +74953218585 +79170123456 
