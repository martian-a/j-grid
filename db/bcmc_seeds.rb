# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rake db:seed (or created alongside the db with db:setup).
#
# Examples:
#
#   cities = City.create([{ :name => 'Chicago' }, { :name => 'Copenhagen' }])
#   Mayor.create(:name => 'Daley', :city => cities.first)

#Firstly lets destroy anything we can find
Room.delete_all
Timeslot.delete_all
Slot.delete_all
Talk.delete_all
User.delete_all

#Generate Rooms
Room.create(:name => 'Red Dwarf', :description => '5th Floor, straight ahead towards Old Trafford. Semi-circle of chairs against the curved glass wall.', :short_code => 'rdw', :capacity => 30, :facilities => 'TV')
Room.create(:name => 'Doctor Who', :description => '5th Floor, large auditorium-like space to the left as you face Old Trafford. Where the welcome talk took place.', :short_code => 'drw', :capacity => 100, :facilities => 'TV')
Room.create(:name => 'Inside Out', :description => '5th Floor, in the rear-left corner as you face Old Trafford.', :short_code => 'ino', :capacity => 20, :facilities => 'Projector')
Room.create(:name => 'Attachments', :description => '5th Floor, in the front-left corner as you face Old Trafford.', :short_code => 'att', :capacity => 50, :facilities => 'Whiteboard')
Room.create(:name => 'Torchwood', :description => '4th Floor, cosy armchairs, to the right as you face Old Trafford, behind the kitchen.', :short_code => 'twd', :capacity => 15, :facilities => 'Projector, whiteboard')
Room.create(:name => 'Bagpuss', :description => '4th Floor, in the front-left corner as you face Old Trafford. Next to Bagpuss.', :short_code => 'bgp', :capacity => 20)
Room.create(:name => 'Redhead', :description => '4th Floor, in the front-left corner as you face Old Trafford. Next to Redhead.', :short_code => 'rdh', :capacity => 30, :facilities => 'TV, whiteboard')

#Generate Timeslots

locked_slots = Array.new

##Opening Talk

start_time = Time.utc(2011, "sep", 17,9,30,00)
end_time = start_time + 40.minutes
timeslot_label = 'Opening Talk'
Timeslot.create(:name => timeslot_label, :start => start_time, :end => end_time)
locked_slots << timeslot_label


## Grid Population

start_time = end_time
end_time = start_time + 20.minutes
timeslot_label = 'Grid Population'
Timeslot.create(:name => timeslot_label, :start => start_time, :end => end_time)
locked_slots << timeslot_label


## Saturday Morning

num_timeslots = 3
session_no = 1

i = 0
while i < num_timeslots

  start_time = end_time
  end_time = start_time + 20.minutes

  Timeslot.create(:name => "Session #{session_no}", :start => start_time, :end => end_time)

  i = i+1
  session_no = session_no+1
  
  end_time = end_time + 10.minutes

end


## Lunch

start_time = end_time
end_time = start_time + 1.hour
timeslot_label = 'Lunch'
Timeslot.create(:name => timeslot_label, :start => start_time, :end => end_time)
locked_slots << timeslot_label


## Saturday Afternoon

num_timeslots = 11

i = 0
while i < num_timeslots

  start_time = end_time
  end_time = start_time + 20.minutes

  Timeslot.create(:name => "Session #{session_no}", :start => start_time, :end => end_time)

  i = i+1
  session_no = session_no+1

  end_time = end_time + 10.minutes

end


## Dinner

start_time = end_time
end_time = start_time + 90.minutes
timeslot_label = 'Dinner'
Timeslot.create(:name => timeslot_label, :start => start_time, :end => end_time)
locked_slots << timeslot_label


## Saturday Evening

num_timeslots = 2

i = 0
while i < num_timeslots

  start_time = end_time
  end_time = start_time + 20.minutes

  Timeslot.create(:name => "Session #{session_no}", :start => start_time, :end => end_time)

  i = i+1
  session_no = session_no+1
  
  end_time = end_time + 10.minutes

end


## Saturday Night

start_time = end_time
end_time = Time.utc(2011, "sep", 18,7,30,00)
timeslot_label = 'Games, etc.'
Timeslot.create(:name => timeslot_label, :start => start_time, :end => end_time)
locked_slots << timeslot_label


## Breakfast

start_time = end_time
end_time = start_time + 90.minutes
timeslot_label = 'Breakfast'
Timeslot.create(:name => timeslot_label, :start => start_time, :end => end_time)
locked_slots << timeslot_label


## Sunday Morning

num_timeslots = 4

i = 0
while i < num_timeslots

  start_time = end_time
  end_time = start_time + 20.minutes

  Timeslot.create(:name => "Session #{session_no}", :start => start_time, :end => end_time)

  i = i+1
  session_no = session_no+1
  
  end_time = end_time + 10.minutes

end


## Lunch

start_time = end_time
end_time = start_time + 1.hour
timeslot_label = 'Lunch'
Timeslot.create(:name => timeslot_label, :start => start_time, :end => end_time)


## Sunday Afternoon

num_timeslots = 4

i = 0
while i < num_timeslots

  start_time = end_time
  end_time = start_time + 20.minutes

  Timeslot.create(:name => "Session #{session_no}", :start => start_time, :end => end_time)

  i = i+1
  session_no = session_no+1
  
  if (i == (num_timeslots - 1))
  	end_time = end_time + 15.minutes
  else
  	end_time = end_time + 10.minutes
  end	
  

end


## Closing Talk

start_time = end_time
end_time = start_time + 15.minutes
timeslot_label = 'Closing Talk'
Timeslot.create(:name => timeslot_label, :start => start_time, :end => end_time)
locked_slots << timeslot_label


## Clean-up

start_time = end_time
end_time = start_time + 30.minutes
timeslot_label = 'Clean-up'
Timeslot.create(:name => timeslot_label, :start => start_time, :end => end_time)
locked_slots << timeslot_label


#Generate Slots

puts "Total Timeslots: " + Timeslot.all.count.to_s
puts "ID of first Timeslot: " + Timeslot.all.first.id.to_s
puts "Total Rooms: " + Room.all.count.to_s
puts "ID of first Room: " + Room.all.first.id.to_s
puts "Total Slots: " + Slot.all.count.to_s  

Slot.generate!

puts "Total Slots: " + Slot.all.count.to_s  

Timeslot.all.each do |timeslot|

	if (locked_slots.include?(timeslot.name))
	
		timeslot.slots.each do |slot|
			
			talk = Talk.create(:title => timeslot.name)
			slot.talk_id = talk.id
			slot.locked = true
			slot.save
		
		end			
	
	end

end