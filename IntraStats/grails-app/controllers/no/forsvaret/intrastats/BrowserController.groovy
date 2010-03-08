package no.forsvaret.intrastats


class BrowserController {

    def index = {
        def colors = ["AFD8F8", "F6BD0F", "8BBA00", "FF8E46", "008E8E", "D64646", "8E468E", "588526", "B3AA00", "008ED6", "9D080D", "A186BE"]
        def browsers = ["MSIE 5.0":["Internet Explorer 5.0", 0],
                            "MSIE 5.5":["Internet Explorer 5.5", 0],
                            "MSIE 6.0":["Internet Explorer 6.0", 0],
                            "MSIE 7.0":["Internet Explorer 7.0", 0],
                            "MSIE 8.0":["Internet Explorer 8.0", 0],
                            "firefox":["Firefox", 0],
                            "safari":["Safari", 0],
                            "opera":["Opera", 0],
                            "chrome":["Chrome", 0],
                            "lynx":["Lynx", 0],
                            "__OTHER":["Other", 0]]
        Client.list().each() {
            def found = false
            browsers.each() { key, value ->
                if (it.userAgent.contains(key)) {
                    value[1]++
                    found = true
                }
            }
            if (!found) {
                browsers.get("__OTHER")[1]++
            }
        }

        def browserData = "<graph bgAlpha='0' caption='Browser usage' decimalPrecision='0' formatNumberScale='0'>"
        def i = 0
        browsers.each() { key, value ->
            if (value[1] > 0) {
                def color = colors[i++]
                if (i >= colors.size()) {
                    i = 0
                }
                browserData += "<set name='" + value[0] + "' value='" + value[1] + "' color='" + color + "' />"
            }
        }
        browserData += "</graph>"
        [browserData: browserData]
    }
}
