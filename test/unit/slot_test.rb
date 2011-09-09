require 'test_helper'

class SlotTest < ActiveSupport::TestCase
  test "can get the slots in the current and next timeslots (relative to now)" do
    
    now_ts = Timeslot.all.first
    next_ts = Timeslot.all.second
    
    #Start the day
    Timecop.travel(now_ts.start)
    
    
    assert Slot.on_now.first.timeslot_id == now_ts.id
    assert Slot.on_next.first.timeslot_id == next_ts.id
    
    Timecop.return()
    
  end
end
