# TextBuilder Library
The **TextBuilder** library simplifies the creation and styling of rich text for Android applications. By chaining methods, developers can construct `SpannableStringBuilder` objects with customized text styles, alignments, and advanced features.

## Key Features

- Comprehensive text styling (size, color, background, font, underline, etc.)
- Alignment options (center, left, right)
- Clickable links and custom click listener support
- Chaining API for intuitive and readable code

## Getting Started

### Adding TextBuilder to Your Project
Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```kotlin
	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```
Add the dependency
```kotlin
dependencies {
   implementation 'com.github.LimGyeongMo:TextBuilder:Version'
}
```
### Basic Usage

Here is an example of how to use `TextBuilder` to create a styled text block:

```kotlin
val styledText = TextBuilder.builder()
    .setDefaultAttribute(
        TextStyle.builder()
            .textSize(16)
            .textColor(ContextCompat.getColor(context, R.color.black))
            .align(TextStyle.ALIGN_CENTER)
    )
    .text("This is default styled text.")
    .space()
    .text("This is larger and custom-font text.",
        TextStyle.builder()
            .textSize(22)
            .font(context, R.font.custom_font)
    )
    .newLine()
    .text("Click me!",
        TextStyle.builder()
            .setClickListener(textView, View.OnClickListener {
                Toast.makeText(context, "Clicked!", Toast.LENGTH_SHORT).show()
            })
    )
    .build()

textView.text = styledText
```

## Components and API

### TextBuilder

The primary class used to construct and style text.

#### Methods

- `builder()`: Creates a new `TextBuilder` instance.
- `setDefaultAttribute(TextStyle?)`: Sets default attributes for text sections.
- `text(String, TextStyle?)`: Adds a text section with optional custom styling.
- `space(Int)`: Inserts a specified number of spaces.
- `newLine(Int)`: Inserts a specified number of new lines.
- `tab(Int)`: Adds a specified number of tab characters.
- `setAlign(Any?)`: Sets text alignment for the entire block.
- `build()`: Returns the `SpannableStringBuilder` object.

### TextStyle

Defines styles for individual text segments.

#### Methods

- `textSize(Int)`: Sets the size of the text.
- `textColor(Int)`: Defines the text color.
- `backgroundColor(Int)`: Sets the background color.
- `underline()`: Adds an underline to the text.
- `underline(Context, Int, Int)`: Adds a custom underline with specified color and thickness.
- `font(Context, Int)`: Applies a custom font.
- `setStyle(Int)`: Configures text style (e.g., bold, italic).
- `link(TextView, String)`: Adds a clickable link.
- `align(AlignmentSpan.Standard)`: Sets the text alignment.
- `letterSpacing(Float)`: Adjusts the spacing between letters.
- `setClickListener(TextView, View.OnClickListener)`: Adds a click listener to the text.

### Predefined Alignments

- `TextStyle.ALIGN_CENTER`
- `TextStyle.ALIGN_RIGHT`
- `TextStyle.ALIGN_LEFT`

## Example Use Case

```kotlin
TextBuilder.builder()
    .setDefaultAttribute(
        TextStyle.builder()
            .textSize(14)
            .textColor(Color.GRAY)
    )
    .text("Welcome to TextBuilder!")
    .text("Features include:",
        TextStyle.builder()
            .textSize(18)
            .setStyle(Typeface.BOLD)
    )
    .newLine()
    .text("- Easy chaining API")
    .newLine()  
    .text("- Custom styling options", TextStyle.builder().textColor(Color.BLUE))
     .newLine()  
    .text("- Clickable text",
        TextStyle.builder()
            .underline()
            .setClickListener(textView, View.OnClickListener {
        Toast.makeText(context, "You clicked!", Toast.LENGTH_SHORT).show()
            })
    )
    .build()
```

## Notes

- Ensure proper context and resource management when using fonts, colors, and click listeners.
- Inject LinkMovementMethod into the TextView to ensure clickable spans work correctly.

## License

Provide licensing details if applicable.

