### Java API Reference (Android)

### API for React Native 0.60 version and above

Since `autolinking` uses `react-native.config.js` to link plugins, constructors are specified in that file. But you can override custom variables to manage the CodePush plugin by placing these values in string resources.

* __Server Url__ - used for specifying CodePush Server Url.
    The Default value: "https://codepush.appcenter.ms/" is overridden by adding your path to `strings.xml` with name `CodePushServerUrl`. CodePush automatically gets this property and will use this path to send requests. For example:
    ```xml
    <string moduleConfig="true" name="CodePushServerUrl">https://yourcodepush.server.com</string>
    ```