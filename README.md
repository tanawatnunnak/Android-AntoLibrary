# Android-AntoLibrary
  AntoLibrary for android communicate anto to read or update data anto. 
  For more detail on the anto https://www.anto.io/.
# Download

add permission.INTERNET in manifest
```
<manifest>
    <uses-permission android:name="android.permission.INTERNET"/>
    ...
</manifest>
```
build.gradle (Project)
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
build.gradle (Module)
```
dependencies {
    implementation 'com.github.tanawatnunnak:Android-AntoLibrary:master'
}
```

# Usage
**Reference**
```
 val reference = Anto.getInstane().getReference(KEY, THING, CHANNEL)
```
or not add channel for multi channel

```
 val reference = Anto.getInstane().getReference(KEY, THING)
 val temperature = reference.addChannel("temperature")
 val bedroom = reference.addChannel("light_bedroom")
```
**SetValue**
```
bedroom.setValue("1")
```
**Listener**
<br/>

read data one time
```
 temperature.addSingleValueEventListener(object : ValueEventListener {
            override fun onDataChange(responseAnto: ResponseAnto) {
                text_value_humidity.text = responseAnto.value
            }
            override fun onCancelled(dataBaseError: String) {
            }
        })
```

read data Realtime
```
 temperature.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(responseAnto: ResponseAnto) {
                text_value_humidity.text = responseAnto.value
            }
            override fun onCancelled(dataBaseError: String) {
            }
        })
```
remove listener

```
temperature.removeEventListener()
```

