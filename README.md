# xml_web_parser

Cоздать xml-файл, хранящий информацию об объектах определенной предметной области. Для валидации полученного xml-файла необходимо разработать соответствующую ему схему xsd. Выполнить парсинг xml-документа с использованием DOM, SAX, StAX парсеров.
Файл загружать в веб-приложение через страницу в браузере, на странице осуществлять выбор парсера. Результаты парсинга должны быть выведены в браузер в виде таблицы.

 использовать для атрибутов required & optional

 перечисления

 шаблоны и предельные значения

 использовать тип ID

 задание значений атрибутов по умолчанию

 расширение типов (имитация наследования)

 использовать дату-время

 создать в xml-документе не менее 16 сущностей

 парсеры организовать с помощью шаблона Builder

 для записи логов использовать Log4J2

 код должен быть покрыт тестами

Вариант 3
Тарифы
Тарифы мобильных компаний:

Name – название тарифа.

Operator name – название сотового оператора, которому принадлежит тариф.

Payroll – абонентская плата в месяц (0 – n рублей).

Сall prices (должно быть несколько) – цены на звонки: внутри сети (0 – n рублей в минуту), вне сети (0 – n рублей в минуту), на стационарные телефоны (0 – n рублей в минуту).

SMS price – цена за смс (0 – n рублей).

Parameters (должно быть несколько) – наличие любимого номера (0 – n), тарификация (12-секундная, минутная), плата за подключение к тарифу (0 – n рублей).

Корневой элемент назвать Tariffs.
