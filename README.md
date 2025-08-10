# KUnit

[![CI](https://github.com/JSpiner/kunit/actions/workflows/ci.yml/badge.svg)](https://github.com/JSpiner/kunit/actions/workflows/ci.yml)
[![codecov](https://codecov.io/gh/JSpiner/kunit/branch/main/graph/badge.svg)](https://codecov.io/gh/JSpiner/kunit)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Kotlin](https://img.shields.io/badge/kotlin-1.9.22-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![Platform](https://img.shields.io/badge/platform-JVM-orange.svg)](https://www.java.com/)

KUnit is a type-safe, zero-overhead unit conversion library for Kotlin/JVM. It provides an intuitive DSL for working with measurements and units, leveraging Kotlin's powerful type system and inline classes for maximum performance with zero runtime overhead.

## Features

- 🚀 **Zero Runtime Overhead** - Uses Kotlin inline value classes
- 🔒 **Type Safety** - Compile-time unit checking prevents unit mismatch errors
- 🎯 **Intuitive DSL** - Natural syntax like `5.km`, `30.minutes`, `25.celsius`
- 📐 **Comprehensive Units** - Distance, Duration, Mass, Temperature, and more
- 🔄 **Seamless Conversions** - Automatic unit conversions with type safety
- 🧮 **Operator Overloading** - Natural arithmetic operations (`5.km + 300.m`)
- 🌍 **Internationalization** - Locale-aware formatting
- ⚡ **High Performance** - All operations optimized to < 1μs
- 🔌 **Standard Library Integration** - Works with `java.time` and `kotlin.time`

## Installation

### Gradle (Kotlin DSL)

```kotlin
// build.gradle.kts
repositories {
    mavenCentral()
}

dependencies {
    implementation("com.kunit:kunit:1.0.0")
}
```

### Gradle (Groovy)

```gradle
// build.gradle
repositories {
    mavenCentral()
}

dependencies {
    implementation 'com.kunit:kunit:1.0.0'
}
```

### Maven

```xml
<dependency>
    <groupId>com.kunit</groupId>
    <artifactId>kunit</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle Version Catalog (TOML)

```toml
# gradle/libs.versions.toml
[versions]
kunit = "1.0.0"

[libraries]
kunit = { module = "com.kunit:kunit", version.ref = "kunit" }
```

```kotlin
// build.gradle.kts
dependencies {
    implementation(libs.kunit)
}
```

## Quick Start

### Basic Usage

```kotlin
import com.kunit.units.distance.*
import com.kunit.units.duration.*
import com.kunit.units.mass.*
import com.kunit.units.temperature.*

fun main() {
    // Creating measurements
    val distance = 5.km
    val time = 30.minutes
    val mass = 2.5.kg
    val temp = 25.celsius
    
    // Automatic conversions
    println(distance.toM())  // 5000.0
    println(time.toSeconds()) // 1800.0
    println(mass.toG())      // 2500.0
    println(temp.toFahrenheit()) // 77.0
}
```

### Arithmetic Operations

```kotlin
// Addition and subtraction with automatic unit conversion
val totalDistance = 5.km + 300.m  // = 5.3 km
val remainingTime = 2.hours - 30.minutes  // = 1.5 hours

// Multiplication and division
val speed = 100.km / 2.hours  // = 50 km/h
val doubleDistance = 5.km * 2  // = 10 km

// Comparisons
val isLonger = 5.km > 3000.m  // true
val isShorter = 100.m < 1.km  // true
```

### Temperature Operations

Temperature has special handling for absolute vs relative operations:

```kotlin
val morning = 20.celsius
val afternoon = 25.celsius

// Temperature difference (relative)
val warming = afternoon - morning  // 5°C difference

// Adding difference to temperature
val evening = afternoon + 3.celsius  // 28°C

// Converting between scales
val inFahrenheit = morning.toFahrenheit()  // 68°F
val inKelvin = morning.toKelvin()  // 293.15K
```

### Collections and Ranges

```kotlin
// Working with collections
val distances = listOf(5.km, 3.km, 7.km)
val total = distances.sum()  // 15 km
val average = distances.average()  // 5 km
val shortest = distances.min()  // 3 km

// Ranges and progressions
val range = 1.km..10.km
val inRange = 5.km in range  // true

// Iteration with step
for (d in (0.m..100.m) step 10.m) {
    println(d)  // 0m, 10m, 20m, ... 100m
}
```

### Formatting

```kotlin
val distance = 1234.567.m

// Default formatting
println(distance.format())  // "1234.567 m"

// Precision control
println(distance.formatWithPrecision(2))  // "1234.57 m"

// Compact formatting (auto-selects best unit)
println(distance.formatCompact())  // "1.23 km"

// Locale-specific formatting
val formatter = LocaleFormat(Locale.FRANCE)
println(distance.format(formatter))  // "1 234,567 m"

// Custom pattern
val pattern = PatternFormat("###,##0.00")
println(distance.format(pattern))  // "1,234.57 m"
```

## Integration with Java Time

KUnit seamlessly integrates with `java.time` APIs:

```kotlin
import java.time.LocalDateTime
import com.kunit.units.duration.*

val now = LocalDateTime.now()
val later = now + 2.hours + 30.minutes

val duration = 90.minutes
val javaDuration = duration.toJavaDuration()  // java.time.Duration
val kunitDuration = javaDuration.toKUnitDuration()  // back to KUnit
```

## Integration with Kotlin Time

KUnit also works with `kotlin.time`:

```kotlin
import kotlin.time.Duration as KotlinDuration
import com.kunit.units.duration.*

val kunitDuration = 1.hours + 30.minutes
val kotlinDuration = kunitDuration.toKotlinDuration()

// Convert back
val backToKunit = kotlinDuration.toKUnitDuration()
```

## Supported Units

### Distance
- **Metric**: millimeter (mm), centimeter (cm), meter (m), kilometer (km)
- **Imperial**: inch (in), foot (ft), yard (yd), mile (mi)

### Duration
- nanosecond, microsecond, millisecond, second, minute, hour, day, week

### Mass
- **Metric**: milligram (mg), gram (g), kilogram (kg), metric ton (t)
- **Imperial**: ounce (oz), pound (lb)

### Temperature
- Celsius (°C), Fahrenheit (°F), Kelvin (K)

## Advanced Usage

### Custom Formatting

```kotlin
// Scientific notation
val formatter = DecimalFormat(
    decimalPlaces = 2,
    useScientificNotation = true
)
println(1234567.m.format(formatter))  // "1.23e6 m"

// Without unit symbol
val noUnit = DecimalFormat(showUnitSymbol = false)
println(5.km.format(noUnit))  // "5000.0"

// Always show sign
val withSign = DecimalFormat(alwaysShowSign = true)
println(5.km.format(withSign))  // "+5000.0 m"
```

### Property-Based Operations

```kotlin
// Finding the closest standard distance
val distance = 1234.m
val rounded = distance.roundToNearest(100.m)  // 1200 m

// Clamping to range
val clamped = distance.coerceIn(1.km..2.km)  // 1234 m (unchanged)
val limited = 3.km.coerceIn(1.km..2.km)  // 2 km
```

## Performance

KUnit is designed for zero runtime overhead:

- All unit types are `@JvmInline value class` - no boxing overhead
- Extension properties are inline functions
- Operator overloading is optimized by the Kotlin compiler
- Typical operations complete in < 1 microsecond

## Contributing

We welcome contributions! Please see [CONTRIBUTING.md](CONTRIBUTING.md) for details.

## License

KUnit is released under the [Apache License 2.0](LICENSE).

## Acknowledgments

KUnit is inspired by:
- [Kotlin Duration API](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.time/-duration/)
- [Units of Measurement (JSR 385)](https://jcp.org/en/jsr/detail?id=385)
- [Squants (Scala)](https://github.com/typelevel/squants)

## Support

- **Issues**: [GitHub Issues](https://github.com/JSpiner/kunit/issues)
- **Discussions**: [GitHub Discussions](https://github.com/JSpiner/kunit/discussions)
- **Documentation**: [API Documentation](https://jspiner.github.io/kunit/)

---

Made with ❤️ using Kotlin