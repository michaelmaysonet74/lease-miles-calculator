package services

import models.Today

import java.time.{LocalDate, ZoneId, ZonedDateTime}
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

trait DateService {

  def getCurrentYear(
    currentMonth: Int
  ): Option[Int]

  def getCurrentMonth: Int

  def getToday: Today

}

class DateServiceImpl extends DateService {

  import DateServiceImpl._

  override def getCurrentYear(
    currentMonth: Int
  ): Option[Int] = {
    val currentYear = currentMonth / 12
    if (currentYear > 0) Some(currentYear) else None
  }

  override def getCurrentMonth: Int =
    getLeaseStartDate
      .map(ChronoUnit.MONTHS.between(_, getNow).toInt + 1)
      .getOrElse(0)

  override def getToday: Today = Today(
    date = getNow.format(DateTimeFormatter.ofPattern("M/d/yyyy")),
    time = getNow.format(DateTimeFormatter.ofPattern("h:mm:ss a"))
  )

  private def getLeaseStartDate: Option[LocalDate] =
    maybeTermStartDate.map(LocalDate.parse(_, dateFormatter))

  private def getNow: ZonedDateTime = ZonedDateTime.now(ZoneId.of(localTimeZone))

}

object DateServiceImpl {

  import scala.sys.env

  private val maybeTermStartDate = env.get("TERM_START_DATE")
  private val localTimeZone = env.getOrElse("LOCAL_TIME_ZONE", "America/New_York")
  private val dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy")

}
