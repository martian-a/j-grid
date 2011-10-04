class Slot < ActiveRecord::Base
  belongs_to :room
  belongs_to :timeslot
  has_one :talk
  
  before_save :expire_cache
  after_save :rebuild_cache

  validates :timeslot_id,
  	:presence => true

  validates :room_id,
  	:presence => true

  def start
    timeslot.start
  end

  def end
    timeslot.end
  end

  def self.generate!

    timeslots = Timeslot.order(:start).all

	puts "Total timeslots: " + timeslots.count.to_s

    Slot.delete_all

    timeslots.each do |timeslot|
    
      puts "Timeslot: " + timeslot.id.to_s
    
      Room.all.each do |room|
        
        puts "Room: " + room.id.to_s
        
        Slot.create(:room_id => room.id, :timeslot_id => timeslot.id)             
        
      end           
    end
  end
  

  def self.find_empty
	@slots = Array.new()
	Slot.all.each do | slot |
		if (slot.is_empty?)
			@slots << slot
		end
	end
  	return @slots
  end

  def is_empty?
    if (self.talk_id.nil?)
      return true
    else
      return false
    end
  end

  def self.by_timeslot(timeslot)
    Slot.joins(:timeslot).where('timeslots.id = ?', timeslot)
  end

  def self.on_now
    Timeslot.on_now.slots
  end

  def self.on_next
    Timeslot.on_next.slots
  end

  private

  def expire_cache
    Rails.cache.delete("slot_#{self.id}")
  end

  def rebuild_cache
    Rails.cache.write("slot_#{self.id}", self)
  end


end
