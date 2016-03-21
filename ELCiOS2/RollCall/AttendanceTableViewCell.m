//
//  AttendanceTableViewCell.m
//  RollCall
//
//  Created by Eric Wang on 3/3/16.
//  Copyright © 2016 Eric Wang. All rights reserved.
//

#import "AttendanceTableViewCell.h"

@implementation AttendanceTableViewCell

- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier {
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    
    if(self) {
    
    self.backgroundColor = [UIColor clearColor];
        
    UIView *backgroundView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 300, 30)];
    backgroundView.backgroundColor = [UIColor colorWithRed:151.0 green:151.0 blue:151.0 alpha:.3];
    backgroundView.layer.cornerRadius = 5;
    backgroundView.layer.masksToBounds = YES;
    [self addSubview:backgroundView];
        
    _nameDateLabel = [[UILabel alloc] initWithFrame:CGRectMake(6, 7, 150, 15)];
    _nameDateLabel.textColor = [UIColor whiteColor];
    _nameDateLabel.font = [UIFont fontWithName:@"HelveticaNeue" size:12];
    [self addSubview:_nameDateLabel];
        
    _attendanceTracker = [[UIImageView alloc] initWithFrame:CGRectMake(276, 6, 18, 18)];
    UIImage *attendanceMark = [UIImage imageNamed:@"Absent"];
    [_attendanceTracker setImage:attendanceMark];
    //[self addSubview:_attendanceTracker];
    
    }
    
    return self;
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];
}

@end
