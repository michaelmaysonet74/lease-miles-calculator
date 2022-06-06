package services

import models.Today

import java.time.{LocalDate, LocalTime}
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

trait DateService {

  def getCurrentMonth: Int

  def getToday: Today

}

class DateServiceImpl extends DateService {

  import DateServiceImpl._

  override def getCurrentMonth: Int =
    getLeaseStartDate
      .map { startDate =>
        ChronoUnit.MONTHS.between(startDate, LocalDate.now()).toInt + 1
      }
      .getOrElse(0)

  override def getToday: Today = Today(
    date = LocalDate.now().format(DateTimeFormatter.ofPattern("M/d/yyyy")),
    time = LocalTime.now().format(DateTimeFormatter.ofPattern("h:m:ss a"))
  )

  private def getLeaseStartDate: Option[LocalDate] =
    maybeTermStartDate.map(LocalDate.parse(_, dateFormatter))

}

object DateServiceImpl {

  import scala.sys.env

  private val maybeTermStartDate = env.get("TERM_START_DATE")
  private val dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy")

}
