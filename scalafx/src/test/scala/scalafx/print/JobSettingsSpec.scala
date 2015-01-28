/*
* Copyright (c) 2011-2014, ScalaFX Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the ScalaFX Project nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE SCALAFX PROJECT OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package scalafx.print

import javafx.{print => jfxp}

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import scalafx.Includes._
import scalafx.testutil.SimpleSFXDelegateSpec

/**
 * Tests for [[scalafx.print.JobSettings]] temporarily inactive.
 *
 * When running in an enviroment with no defined printer,
 * [[http://docs.oracle.com/javase/8/javafx/api/javafx/print/PrinterJob.html#createPrinterJob--
 * PrinterJob.createPrinterJob()]] will return 'null'. Consequently, there will be a
 * 'NullPointerException'. Since JobSettings is a final class, it is not possible create a mock.
 * Therefore, it is necessary to skip the conversion tests when there is no printer defined in 
 * environment.
 */
@RunWith(classOf[JUnitRunner])
class JobSettingsSpec
  extends SimpleSFXDelegateSpec[jfxp.JobSettings, JobSettings](classOf[jfxp.JobSettings], classOf[JobSettings]) {

  val skipingMessage: String = if (jfxp.PrinterJob.createPrinterJob == null
    || jfxp.PrinterJob.createPrinterJob.getJobSettings == null) {
    "Neither Default Printer Job nor Job Settings defined."
  } else {
    ""
  }

  override val skipJfxToSfxCause = skipingMessage

  override val skipSfxToJfxCause = skipingMessage

  override protected def getScalaClassInstance = PrinterJob.createPrinterJob.jobSettings

  override protected def getJavaClassInstance =
    jfxp.PrinterJob.createPrinterJob.getJobSettings

}