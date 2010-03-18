package no.forsvaret.intrastats

class LogFileController {

    final String LOGFILENAME = "IntraStats.log"
    final int TAILSIZE = 1000

    def index = {
        def logFileContent = "No file found!"
        def file = new File(LOGFILENAME)
        def tailSize = params.tailSize != null ? Integer.parseInt(params.tailSize) : TAILSIZE
        if (file.exists()) {
            if (params.wholeFile != null || tailSize >= file.length()) {
                logFileContent = file.getText()
            } else {
                def tail = new RandomAccessFile(LOGFILENAME, "r")
                tail.seek(tail.length() - tailSize)
                def bytes = new byte[tailSize]
                tail.readFully(bytes)
                logFileContent= new String(bytes)
                def split = logFileContent.indexOf('\n')
                if (split >= 0) {
                    logFileContent = logFileContent.substring(split + 1)
                }
            }
        }
        [logFileContent: logFileContent]
    }
}
