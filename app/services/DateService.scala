package services

import models.Today

import java.time.{LocalDate, ZonedDateTime, ZoneId}
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

trait DateService {

  def getCurrentYear(): Option[Int]

  def getCurrentMonth(): Int

  def getToday(): Today

}

class DateServiceImpl extends DateService {

  import DateServiceImpl._

  override def getCurrentYear(): Option[Int] =
    getLeaseStartDate()
      .flatMap { startDate =>
        val currentYear = ChronoUnit.YEARS.between(startDate, getNow()).toInt
        if (currentYear > 0) Some(currentYear) else None
      }

  override def getCurrentMonth(): Int =
    getLeaseStartDate()
      .map(ChronoUnit.MONTHS.between(_, getNow()).toInt + 1)
      .getOrElse(0)

  override def getToday(): Today = Today(
    date = getNow().format(DateTimeFormatter.ofPattern("M/d/yyyy")),
    time = getNow().format(DateTimeFormatter.ofPattern("h:mm:ss a"))
  )

  private def getLeaseStartDate(): Option[LocalDate] =
    maybeTermStartDate.map(LocalDate.parse(_, dateFormatter))

  private def getNow() = ZonedDateTime.now(ZoneId.of(localTimeZone))

}

object DateServiceImpl {

  import scala.sys.env

  private val maybeTermStartDate = env.get("TERM_START_DATE")
  private val localTimeZone = env.get("LOCAL_TIME_ZONE").getOrElse("America/New_York")
  private val dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy")

}
