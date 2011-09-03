class Grid
  def initialize
    @timeslots = self.timeslots
    @rooms = self.rooms
  end

  def timeslots
    timeslots = Rails.cache.read('timeslots')
    if !timeslots.nil?
      return timeslots
    else
      timeslots = Timeslot.all
      Rails.cache.write('timeslots', timeslots)
      return timeslots
    end
  end


  def rooms
    rooms = Rails.cache.read('rooms')
    if !rooms.nil?
      return rooms
    else
      rooms = Room.all
      Rails.cache.write('rooms', rooms)
      return rooms
    end
  end

  def slot(slot_id)
    slot = Rails.cache.read("slot_#{slot_id}")
    if !slot.nil?
      return slot
    else
      slot = Slot.find(slot_id)
      slot.timeslot
      Rails.cache.write("slot_#{slot_id}", slot)
      return slot
    end
  end

  def talk(slot_id)
    talk = Rails.cache.read("talk_#{slot_id}")
    if !talk.nil?
      return talk
    else
      talk = Talk.find_by_slot_id(slot_id)
      Rails.cache.write("talk_#{slot_id}", talk)
      return talk
    end
  end

end