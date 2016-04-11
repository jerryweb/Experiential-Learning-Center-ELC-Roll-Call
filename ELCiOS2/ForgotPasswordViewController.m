//
//  ForgotPasswordViewController.m
//  RollCall
//
//  Created by Eric Wang on 4/10/16.
//  Copyright © 2016 Eric Wang. All rights reserved.
//

#import "ForgotPasswordViewController.h"

@interface ForgotPasswordViewController () <UITextFieldDelegate>

@property (nonatomic,strong) UITextField *emailTextField;
@property (nonatomic,strong) UIButton *submitButton;
@property (nonatomic,strong) UIButton *cancelButton;

@end

@implementation ForgotPasswordViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    _emailTextField = [[UITextField alloc] initWithFrame:CGRectMake(79, 203, 218, 30)];
    _emailTextField.text = @"Email Address";
    _emailTextField.textColor = [UIColor whiteColor];
    _emailTextField.font = [UIFont fontWithName:@"HelveticaNeue" size:16];
    _emailTextField.delegate = self;
    _emailTextField.tintColor = [UIColor clearColor];
    _emailTextField.layer.borderWidth = .5f;
    _emailTextField.layer.borderColor = [[UIColor whiteColor] CGColor];;
    _emailTextField.autocapitalizationType = UITextAutocapitalizationTypeNone;
    
    // config login button
    _submitButton = [[UIButton alloc] initWithFrame:CGRectMake(79, 263, 218, 36)];
    [_submitButton setTitle:@"Submit" forState:UIControlStateNormal];
    [_submitButton setTitleColor:[UIColor colorWithRed:255/255.0 green:255/255.0 blue:255/255.0 alpha:1.0] forState:UIControlStateNormal];
    [_submitButton setBackgroundColor:[UIColor colorWithRed:80/255.0 green:227/255.0 blue:194/255.0 alpha:1.0]];
    _submitButton.layer.cornerRadius = 10;
    _submitButton.clipsToBounds = YES;
    [_submitButton addTarget:self action:@selector(submit:) forControlEvents:UIControlEventTouchUpInside];
    
    // config register button
    _cancelButton = [[UIButton alloc] initWithFrame:CGRectMake(79, 319, 218, 36)];
    [_cancelButton setTitle:@"Cancel" forState:UIControlStateNormal];
    [_cancelButton setTitleColor:[UIColor colorWithRed:255/255.0 green:255/255.0 blue:255/255.0 alpha:1.0] forState:UIControlStateNormal];
    [_cancelButton setBackgroundColor:[UIColor colorWithRed:80/255.0 green:227/255.0 blue:194/255.0 alpha:1.0]];
    _cancelButton.layer.cornerRadius = 10;
    _cancelButton.clipsToBounds = YES;
    [_cancelButton addTarget:self action:@selector(cancel:) forControlEvents:UIControlEventTouchUpInside];
    
    [self.view addSubview:_emailTextField];
    [self.view addSubview:_submitButton];
    [self.view addSubview:_cancelButton];
}

#pragma mark - button selectors 

- (void)submit:(UIButton *)sender {
    [self.navigationController popToRootViewControllerAnimated:NO];
    UIAlertController *alertController = [UIAlertController alertControllerWithTitle:@"Email Sent" message:@"Check Email for Further Instructions" preferredStyle:UIAlertControllerStyleAlert];
    UIAlertAction *alertAction = [UIAlertAction actionWithTitle:@"OK" style:UIAlertActionStyleDefault handler:nil];
    [alertController addAction:alertAction];
    [self presentViewController:alertController animated:YES completion:nil];
}

- (void)cancel:(UIButton *)sender {
    [self.navigationController popToRootViewControllerAnimated:NO];
}

- (void)textFieldDidBeginEditing:(UITextField *)textField {
    [textField setText:@""];
    textField.autocorrectionType = UITextAutocorrectionTypeNo;
}

@end
