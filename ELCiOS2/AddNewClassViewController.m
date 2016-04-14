//
//  AddNewClassViewController.m
//  RollCall
//
//  Created by Eric Wang on 4/10/16.
//  Copyright © 2016 Eric Wang. All rights reserved.
//

#import "AddNewClassViewController.h"
#import "ClassSession.h"

@protocol AddNewClassViewControllerDelegate <NSObject>

- (void)addNewItemViewController:(AddNewClassViewController *)controller didFinishEnteringItem:(ClassSession *)class;

@end

@interface AddNewClassViewController () <UITextFieldDelegate>

@property (nonatomic,weak) id <AddNewClassViewControllerDelegate> delegate;
@property (nonatomic,strong) UITextField *UUIDField;
@property (nonatomic,strong) UITextField *major; //
@property (nonatomic,strong) UITextField *minor; //
@property (nonatomic,strong) UITextField *classNameField;
@property (nonatomic,strong) UITextField *firstNameField;
@property (nonatomic,strong) UITextField *lastNameField;
@property (nonatomic,strong) UITextField *emailField;
@property (nonatomic,strong) UILabel *startLabel;
@property (nonatomic,strong) UILabel *endLabel;
@property (nonatomic,strong) UIDatePicker *startTime;
@property (nonatomic,strong) UIDatePicker *endTime;
@property (nonatomic,strong) UITextField *ELCRoom; //
@property (nonatomic,strong) UIButton *submitButton;
@property (nonatomic,strong) UIButton *cancelButton;

@end

@implementation AddNewClassViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    _UUIDField = [[UITextField alloc] initWithFrame:CGRectMake(35, 90, 300, 30)];
    _UUIDField.text = @" UUID";
    _UUIDField.textColor = [UIColor whiteColor];
    _UUIDField.font = [UIFont fontWithName:@"HelveticaNeue" size:16];
    _UUIDField.delegate = self;
    _UUIDField.tintColor = [UIColor clearColor];
    _UUIDField.layer.borderWidth = .5f;
    _UUIDField.layer.borderColor = [[UIColor whiteColor] CGColor];;
    _UUIDField.autocapitalizationType = UITextAutocapitalizationTypeNone;
    
    _major = [[UITextField alloc] initWithFrame:CGRectMake(35, 135, 300, 30)];
    _major.text = @" Major";
    _major.textColor = [UIColor whiteColor];
    _major.font = [UIFont fontWithName:@"HelveticaNeue" size:16];
    _major.delegate = self;
    _major.tintColor = [UIColor clearColor];
    _major.layer.borderWidth = .5f;
    _major.layer.borderColor = [[UIColor whiteColor] CGColor];;
    _major.autocapitalizationType = UITextAutocapitalizationTypeNone;
    
    _minor = [[UITextField alloc] initWithFrame:CGRectMake(35, 180, 300, 30)];
    _minor.text = @" Minor";
    _minor.textColor = [UIColor whiteColor];
    _minor.font = [UIFont fontWithName:@"HelveticaNeue" size:16];
    _minor.delegate = self;
    _minor.tintColor = [UIColor clearColor];
    _minor.layer.borderWidth = .5f;
    _minor.layer.borderColor = [[UIColor whiteColor] CGColor];;
    _minor.autocapitalizationType = UITextAutocapitalizationTypeNone;
    
    _classNameField = [[UITextField alloc] initWithFrame:CGRectMake(35, 225, 300, 30)];
    _classNameField.text = @" Class Name";
    _classNameField.textColor = [UIColor whiteColor];
    _classNameField.font = [UIFont fontWithName:@"HelveticaNeue" size:16];
    _classNameField.delegate = self;
    _classNameField.tintColor = [UIColor clearColor];
    _classNameField.layer.borderWidth = .5f;
    _classNameField.layer.borderColor = [[UIColor whiteColor] CGColor];;
    _classNameField.autocapitalizationType = UITextAutocapitalizationTypeNone;
    
    _firstNameField = [[UITextField alloc] initWithFrame:CGRectMake(35, 270, 300, 30)];
    _firstNameField.text = @" Instructor First Name";
    _firstNameField.textColor = [UIColor whiteColor];
    _firstNameField.font = [UIFont fontWithName:@"HelveticaNeue" size:16];
    _firstNameField.delegate = self;
    _firstNameField.tintColor = [UIColor clearColor];
    _firstNameField.layer.borderWidth = .5f;
    _firstNameField.layer.borderColor = [[UIColor whiteColor] CGColor];;
    _firstNameField.autocapitalizationType = UITextAutocapitalizationTypeNone;
    
    _lastNameField = [[UITextField alloc] initWithFrame:CGRectMake(35, 315, 300, 30)];
    _lastNameField.text = @" Instructor Last Name";
    _lastNameField.textColor = [UIColor whiteColor];
    _lastNameField.font = [UIFont fontWithName:@"HelveticaNeue" size:16];
    _lastNameField.delegate = self;
    _lastNameField.tintColor = [UIColor clearColor];
    _lastNameField.layer.borderWidth = .5f;
    _lastNameField.layer.borderColor = [[UIColor whiteColor] CGColor];;
    _lastNameField.autocapitalizationType = UITextAutocapitalizationTypeNone;
    
    _emailField = [[UITextField alloc] initWithFrame:CGRectMake(35, 360, 300, 30)];
    _emailField.text = @" Instructor Email Address";
    _emailField.textColor = [UIColor whiteColor];
    _emailField.font = [UIFont fontWithName:@"HelveticaNeue" size:16];
    _emailField.delegate = self;
    _emailField.tintColor = [UIColor clearColor];
    _emailField.layer.borderWidth = .5f;
    _emailField.layer.borderColor = [[UIColor whiteColor] CGColor];;
    _emailField.autocapitalizationType = UITextAutocapitalizationTypeNone;
    
    _ELCRoom = [[UITextField alloc] initWithFrame:CGRectMake(35, 405, 300, 30)];
    _ELCRoom.text = @" ELC Room";
    _ELCRoom.textColor = [UIColor whiteColor];
    _ELCRoom.font = [UIFont fontWithName:@"HelveticaNeue" size:16];
    _ELCRoom.delegate = self;
    _ELCRoom.tintColor = [UIColor clearColor];
    _ELCRoom.layer.borderWidth = .5f;
    _ELCRoom.layer.borderColor = [[UIColor whiteColor] CGColor];;
    _ELCRoom.autocapitalizationType = UITextAutocapitalizationTypeNone;
    
    _startLabel = [[UILabel alloc] initWithFrame:CGRectMake(35, 440, 100, 50)];
    _startLabel.text = @"Start Time";
    _startLabel.textColor = [UIColor whiteColor];
    _startLabel.font = [UIFont fontWithName:@"HelveticaNeue" size:16];
    
    _startTime = [[UIDatePicker alloc] initWithFrame:CGRectMake(150, 440, 220, 50)];
    _startTime.datePickerMode = UIDatePickerModeTime;
    [_startTime setValue:[UIColor whiteColor] forKey:@"textColor"];
    
    _endLabel = [[UILabel alloc] initWithFrame:CGRectMake(35, 485, 100, 50)];
    _endLabel.text = @"End Time";
    _endLabel.textColor = [UIColor whiteColor];
    _endLabel.font = [UIFont fontWithName:@"HelveticaNeue" size:16];
    
    _endTime = [[UIDatePicker alloc] initWithFrame:CGRectMake(150, 485, 220, 50)];
    _endTime.datePickerMode = UIDatePickerModeTime;
    [_endTime setValue:[UIColor whiteColor] forKey:@"textColor"];
    
    _submitButton = [[UIButton alloc] initWithFrame:CGRectMake(35, 540, 300, 30)];
    [_submitButton setTitle:@"Submit" forState:UIControlStateNormal];
    [_submitButton setTitleColor:[UIColor colorWithRed:255/255.0 green:255/255.0 blue:255/255.0 alpha:1.0] forState:UIControlStateNormal];
    [_submitButton setBackgroundColor:[UIColor colorWithRed:80/255.0 green:227/255.0 blue:194/255.0 alpha:1.0]];
    _submitButton.layer.cornerRadius = 10;
    _submitButton.clipsToBounds = YES;
    [_submitButton addTarget:self action:@selector(submitNewClass:) forControlEvents:UIControlEventTouchUpInside];
    
    _cancelButton = [[UIButton alloc] initWithFrame:CGRectMake(35, 585, 300, 30)];
    [_cancelButton setTitle:@"Cancel" forState:UIControlStateNormal];
    [_cancelButton setTitleColor:[UIColor colorWithRed:255/255.0 green:255/255.0 blue:255/255.0 alpha:1.0] forState:UIControlStateNormal];
    [_cancelButton setBackgroundColor:[UIColor colorWithRed:80/255.0 green:227/255.0 blue:194/255.0 alpha:1.0]];
    _cancelButton.layer.cornerRadius = 10;
    _cancelButton.clipsToBounds = YES;
    [_cancelButton addTarget:self action:@selector(cancelNewClass:) forControlEvents:UIControlEventTouchUpInside];
    
    [self.view addSubview:_UUIDField];
    [self.view addSubview:_major];
    [self.view addSubview:_minor];
    [self.view addSubview:_classNameField];
    [self.view addSubview:_firstNameField];
    [self.view addSubview:_lastNameField];
    [self.view addSubview:_emailField];
    [self.view addSubview:_startLabel];
    [self.view addSubview:_startTime];
    [self.view addSubview:_endLabel];
    [self.view addSubview:_endTime];
    [self.view addSubview:_ELCRoom];
    [self.view addSubview:_submitButton];
    [self.view addSubview:_cancelButton];
    
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self
                                                                          action:@selector(dismissKeyboard)];
    [self.view addGestureRecognizer:tap];
}

#pragma mark - button selectors

- (void)submitNewClass:(UIButton *)sender {
    NSString *className = _classNameField.text;
    NSString *prefix = @"Professor ";
    NSString *instructorName = [[[prefix stringByAppendingString:_firstNameField.text] stringByAppendingString:@" "] stringByAppendingString:_lastNameField.text];
    NSString *time = @"2-4pm";
    NSString *elc = _ELCRoom.text;
    
    ClassSession *newClass = [[ClassSession alloc] initWithClassName:@"test" withInstructorName:@"test" withStartTime:@"test" withEndTime:@"test" withDate:@"test" withELCRoom:@"test"];
    [self.delegate addNewItemViewController:self didFinishEnteringItem:newClass];
    
    [self.navigationController popViewControllerAnimated:NO];
    UIAlertController *alertController = [UIAlertController alertControllerWithTitle:@"Class Added" message:@"Successfully Added to Class List" preferredStyle:UIAlertControllerStyleAlert];
    UIAlertAction *alertAction = [UIAlertAction actionWithTitle:@"OK" style:UIAlertActionStyleDefault handler:nil];
    [alertController addAction:alertAction];
    [self presentViewController:alertController animated:YES completion:nil];
}

- (void)cancelNewClass:(UIButton *)sender {
    [self.navigationController popViewControllerAnimated:NO];
}

#pragma mark - textfield delegate methods

- (void)textFieldDidBeginEditing:(UITextField *)textField {
    [textField setText:@""];
    textField.autocorrectionType = UITextAutocorrectionTypeNo;
}

- (BOOL) textFieldShouldReturn: (UITextField *) textField {
    [[UIApplication sharedApplication] sendAction:@selector(resignFirstResponder) to:nil from:nil forEvent:nil];
    return YES;
}

#pragma mark - dismiss keyboard

- (void)dismissKeyboard {
    [[UIApplication sharedApplication] sendAction:@selector(resignFirstResponder) to:nil from:nil forEvent:nil];
}

@end
