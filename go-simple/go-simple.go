package main

import (
	"fmt"
	joonix "github.com/joonix/log"
	"github.com/sirupsen/logrus"
	"log"
	"os"
)

func main() {

	// Not the way to go
	fmt.Println("Go Fluentd Test - without any logger")

	customFormatter := new(logrus.TextFormatter)
	customFormatter.TimestampFormat = "2006-01-02 15:04:05"
	logrus.SetFormatter(customFormatter)
	// Output to stdout instead of the default stderr
	// Can be any io.Writer, see below for File example
	logrus.SetOutput(os.Stdout)

	// Only log the warning severity or above.
	logrus.SetLevel(logrus.TraceLevel)

	// Standard log has no Warning
	log.Println("Go Fluentd Test - with In built logger")

	// Proper logging
	logrus.Trace("Go Fluentd Test With Logrus TRACE - very fine details")
	logrus.Debug("Go Fluentd Test With Logrus TRACE - very fine details - Debugging")

	customFormatter.FullTimestamp = true
	logrus.Info("Go Fluentd Test With Logrus TRACE - very fine details - Something Noteworthy")
	logrus.Warn("Go Fluentd Test With Logrus TRACE - very fine details This is Imp Out of Ordinary")
	logrus.Error("Go Fluentd Test With Logrus TRACE - Error but Not Fatal")

	//Still better
	logrus.SetFormatter(joonix.NewFormatter())
	logrus.Info("Go Fluentd Test With Logrus JSON format fluentd")
	logrus.Warn("Go Fluentd Test With Logrus JSON format Warn ")

}
