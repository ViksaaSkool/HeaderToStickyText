## Header to Sticky Text

<img alt="logo" height="220" src="pics/github_repo_pic.png" width="220"/>

------

### Demo
<img src="pics/main_screen.gif" width="200" height="400"/> <img src="pics/button_1.gif" width="200" height="400"/> <img src="pics/button_2.gif" width="200" height="400"/>

------
### Use

The very basic usage: 

```kotlin

HeaderToSticky(
    headerContentBackgroundColor = MaterialTheme.colorScheme.tertiary,
    stickyText = "Sticky Text",
    headerContent = {
        Text(
            modifier = Modifier.padding(32.dp),
            text = "Header Text",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    },
    containerContent = {
        for (i in 1..50) {
            Text(text = "Content item$i")
        }
    }
)

````

The component is simple and straightforward to use. You need to call the `HeaderToSticky` method in your Composable and define the parameters to match your use case.
- `headerContent`: The content (`@Composable`) of the header that will be displayed at the top of the screen
- `stickyContent`: The content (`@Composable`) of the sticky text that will be displayed when the header is scrolled out of the screen. If you don't want anything custom defined you can just populate `stickyText` param instead
- `containerContent`: The content (`@Composable`) of the container that will be displayed below the header and sticky text

Additionally, `scrollAction`, `onScrollStateChange` and `onScrollAction` can be used to define custom actions when the user scrolls the screen.
The example of this is in the third gif (left-to-right), when the user clicks the `FloatingActionButton` the screen scrolls to the bottom.


Other params that can influence the UI and you can override accordingly.

------
### License
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)


[Read More](LICENSE)

------
# Developed By
Viktor Arsovski
</br>
<a href="https://mk.linkedin.com/in/varsovski">
<img alt="Add me to Linkedin" src="http://is.gd/u42ILV" width="96" height="96"/>
</a>