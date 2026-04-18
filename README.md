# Каталог игр (Lab 9, Jetpack Compose)

Приложение показывает одну игру за раз: обложку, название и краткое описание. Кнопки **Предыдущая** и **Следующая** листают каталог по кругу (после последней записи снова первая).

## Использованные элементы Compose

| Элемент | Назначение |
|--------|------------|
| `Column` / `Row` | Вертикальная раскладка экрана и горизонтальная — панель кнопок |
| `Image` | Отображение обложки игры из drawable |
| `Text` | Заголовок и описание |
| `Surface` | Фон/подложка области изображения с лёгким elevation |
| `Spacer` | Отступы между секциями и заполнение пространства перед кнопками |
| `Modifier` | `fillMaxSize`, `fillMaxWidth`, `height`, `padding` |
| `Scaffold` | Каркас экрана с учётом системных отступов |
| `Button` | Кнопки переключения |
| `MaterialTheme` | Типографика (`headlineSmall`, `bodyLarge`) |

Секции вынесены в отдельные компонуемые функции: `GameImageSection`, `GameDescriptionSection`, `GameControlPanel`.

## Данные

- Модель `Game`: идентификаторы строк (`title`, `description`) и drawable для картинки.
- Список из **пяти** игр формируется в `Datasource.loadGames()`.
- Строки лежат в `res/values/strings.xml`, обложки игр — растровые файлы `res/drawable/game1.jpg` … `game5.jpg` (фото-плейсхолдеры с [Lorem Picsum](https://picsum.photos), по лицензии сервиса; для `painterResource` в Compose подходят JPG/PNG/WebP и VectorDrawable, но не XML `<shape>`).
- Иконка приложения: adaptive icon (`mipmap-anydpi-v26`), фиолетовый фон и силуэт геймпада (`drawable/ic_launcher_background.xml`, `ic_launcher_fg.xml`).

## Логика состояния

- Список игр сохраняется в `remember { Datasource().loadGames() }`, чтобы не пересоздавать его при рекомпозиции.
- Текущий индекс хранится в `var currentIndex by remember { mutableStateOf(0) }`.
- **Предыдущая:** если индекс `0`, переход на последний элемент списка, иначе `index - 1`.
- **Следующая:** если индекс последний, переход на `0`, иначе `index + 1`.

## Low-fi прототип

- Текстовая схема: `docs/low_fi_prototype.txt`.
- Рисунок-эскиз для отчёта (как в задании): `docs/low_fi_prototype.png`.

## Сборка

Откройте папку проекта в **Android Studio**, дождитесь синхронизации Gradle, запустите конфигурацию `app`. Требуется **JDK 11+** (рекомендуется встроенный JBR из Android Studio).
