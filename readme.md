## Задание
Разработать web-приложение, содержащее три справочника: профессии, отделы, сотрудники.
Каждый справочник должен содержать операции создания, редактирования, удаления
соответствующей сущности.
Описание сущностей
1. Профессия содержит поля Наименование, Примечание;
2. Отдел содержит поля Наименование, Примечание, Родительский отдел. Т.е. у отделов может
   быть иерархия.
3. Сотрудники содержит поля ФИО, Профессия, Отдел, Примечание
   Используемые технологии
1. Java + Spring Boot;
2. JavaScript + VueJs 3 (дизайн не имеет первостепенного значения);
3. БД H2 embedded;
# Установка
Необходимо склонировать репозиторий 
# Запуск
После клонирования запустить `mvn clean install` для backend. Затем запустить SpringBoot `mvn spingboot:run` и
клиент. Находясь в пакете client `npm run serve`. Доступ к swagger : [SWAGGER Rest Api Documentation](http://localhost:8080/api)
# Особенности работы 
Невозможно создание Employee с новыми Profession и Department, которых нет в б/д. Необходимо сначала создать Profession и Department.