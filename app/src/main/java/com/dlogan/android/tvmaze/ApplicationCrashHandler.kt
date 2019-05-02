package com.dlogan.android.tvmaze

class ApplicationCrashHandler private constructor() : Thread.UncaughtExceptionHandler {
    /**
     * Storage for the original default crash handler.
     */
    private val defaultHandler: Thread.UncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()

    /**
     * Called when there is an uncaught exception elsewhere in the code.
     * @param t the thread that caused the error
     * @param e the exception that caused the error
     */
    override fun uncaughtException(t: Thread, e: Throwable) {
        // Place a breakpoint here to catch application crashes

        // Call the default handler
        defaultHandler.uncaughtException(t, e)
    }

    companion object {


        /**
         * Installs a new exception handler.
         */
        fun installHandler() {
            if (Thread.getDefaultUncaughtExceptionHandler() !is ApplicationCrashHandler) {
                Thread.setDefaultUncaughtExceptionHandler(ApplicationCrashHandler())
            }
        }
    }

}