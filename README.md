1. Main.java: Основной класс приложения, который обрабатывает аргументы командной строки и координирует работу остальных компонентов.

2. ArgumentParser: Отвечает за разбор и обработку аргументов командной строки.
   - ArgumentParserException: Исключение, связанное с ошибками в аргументах командной строки.

3. InputDataHandler: Отвечает за чтение и разделение входных данных по типам.
   - InputDataType: Перечисление, определяющее типы входных данных.

4. OutputDataWriter: Отвечает за запись данных в соответствующие выходные файлы.
   - OutputFile: Представляет один выходной файл с данными определенного типа.

5. Statistics: Отвечает за сбор и вывод статистики по обработанным данным.
   - StatisticsType: Перечисление, определяющее типы статистики.

6. FileUtils: Вспомогательный класс для работы с файлами.
   - DataItem: Представляет одну единицу входных данных (число или строку).
